package ChildCareTech;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.DAO.MealDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.User;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.network.RMI.RMIUserSession;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;

public class GetCanteenByNameTest {
    private SessionFactory sessionFactory;
    private Session session = null;

    @Test
    public void testGetCanteenByName(){
        CanteenDAO canteenDAO = new CanteenDAO();
        MealDAO mealDAO = new MealDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();
        Canteen canteen = new Canteen("nome", null);
        WorkDay w = new WorkDay(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT, false);
        Meal m1 = new Meal(canteen, 0, w, null, null, EventStatus.CLOSED, null);
        Meal m2 = new Meal(canteen, 1, w, null, null, EventStatus.CLOSED, null);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        mealDAO.setSession(session);
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            canteenDAO.create(canteen);
            workDayDAO.create(w);
            mealDAO.create(m1);
            mealDAO.create(m2);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        try {
            RMIUserSession userSession = new RMIUserSession(new User("nome", "pw"));
            CanteenDTO c = userSession.getCanteenByName("nome");
            assert(c.getMeals().size() == 2);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        // setup the session factory
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    @After
    public void tearDown() throws Exception {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("drop database test").executeUpdate();
        tx.commit();
        session.close();

        sessionFactory.close();
    }
}
