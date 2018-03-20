package ChildCareTech.controller;

import ChildCareTech.model.User;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginController {
    public static User login(String username, String password){
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Query query = null;
        User user = null;

        try{
            //DAO
            query = session.createQuery("from User where userName = :un");
            query.setParameter("un", username);

            user = (User) query.uniqueResult();
        } catch(HibernateException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }

        return user;
    }
}
