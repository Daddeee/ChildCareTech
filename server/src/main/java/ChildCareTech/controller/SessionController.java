package ChildCareTech.controller;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.model.user.User;
import ChildCareTech.utils.GenericDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SessionController {
    private static final ConcurrentHashMap<String, UserSession> currentSessions = new ConcurrentHashMap<>();

    public static User getUser(String username, String password) throws LoginFailedException {
        List<User> user;
        Session session;
        GenericDAO<User, Integer> dao;
        Transaction tx = null;
        HashMap<String, String> queryMap = new HashMap<>();


        session = HibernateSessionFactoryUtil.getInstance().openSession();
        dao = new GenericDAO<>(User.class);
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
        GenericDAO<User, Integer> dao = new GenericDAO<>(User.class);
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
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new RegistrationFailedException("Registration failed.");
        }

        return true;
    }

    public static synchronized void storeSession(UserSession session, String sessionKey) throws LoginFailedException {
        if (currentSessions.get(sessionKey) != null) {
            throw new LoginFailedException("Another session associated with the same user is already present");
        }
        currentSessions.put(sessionKey, session);
    }

    public static synchronized void removeSession(String sessionKey) {
        currentSessions.remove(sessionKey);
    }
}
