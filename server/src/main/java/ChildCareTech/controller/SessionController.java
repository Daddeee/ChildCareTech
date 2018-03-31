package ChildCareTech.controller;

import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.model.User;
import ChildCareTech.utils.GenericDao;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SessionController {
    private static final ConcurrentHashMap<String, UserSession> currentSessions = new ConcurrentHashMap<>();

    public static User getUser(String username, String password) throws LoginFailedException{
        List<User> user;
        Session session;
        GenericDao<User, String> dao;
        Transaction tx = null;

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        dao = new GenericDao<>(User.class);
        dao.setSession(session);
        try{
            tx = session.beginTransaction();
            user = dao.read("userName", username);
            tx.commit();
        } catch(HibernateException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }

        if(user == null || user.size() == 0) throw new LoginFailedException("User not found");
        return user.get(0);
    }

    public static synchronized void storeSession(UserSession session, String sessionKey) throws LoginFailedException{
        if(currentSessions.get(sessionKey) != null){
            throw new LoginFailedException("Another session associated with the same user is already present");
        }
        currentSessions.put(sessionKey, session);
    }

    public static synchronized void removeSession(String sessionKey){
        currentSessions.remove(sessionKey);
    }
}
