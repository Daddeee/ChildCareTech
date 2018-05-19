package ChildCareTech.controller;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.model.entities.User;
import ChildCareTech.model.DAO.UserDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private static final ConcurrentHashMap<User, Object> currentSessions = new ConcurrentHashMap<>();

    public static User getUser(String username, String password) throws LoginFailedException {
        List<User> user;
        Session session;
        UserDAO dao;
        Transaction tx = null;
        HashMap<String, String> queryMap = new HashMap<>();


        session = HibernateSessionFactoryUtil.getInstance().openSession();
        dao = new UserDAO();
        dao.setSession(session);
        try {
            tx = session.beginTransaction();
            queryMap.put("userName", username);
            queryMap.put("password", password);
            user = dao.read(queryMap);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }

        if (user == null || user.size() == 0) throw new LoginFailedException("Invalid credentials");
        return user.get(0);
    }

    public static boolean registerUser(String userName, String password) throws RegistrationFailedException {
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        UserDAO dao = new UserDAO();
        dao.setSession(session);
        Transaction tx = null;
        User u = null;
        List<User> userList = null;

        try {
            tx = session.beginTransaction();

            userList = dao.read("userName", userName);
            if (userList != null && userList.size() > 0)
                throw new RegistrationFailedException("Username not available");
            u = new User(userName, password);
            dao.create(u);

            tx.commit();
        } catch (HibernateException | ValidationFailedException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new RegistrationFailedException("Registration failed.");
        }

        return true;
    }

    public static synchronized void storeSession(User user, Object session) throws LoginFailedException {
        if (currentSessions.get(user) != null) {
            throw new LoginFailedException("Another session associated with the same user is already present");
        }
        currentSessions.put(user, session);
    }

    public static synchronized void removeSession(User user) {
        currentSessions.remove(user);
    }
}
