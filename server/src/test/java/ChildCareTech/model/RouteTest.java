package ChildCareTech.model;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.DAO.RouteDAO;
import ChildCareTech.model.entities.Trip;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class RouteTest extends AbstractEntityTest<Route, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new RouteDAO();
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1), EventStatus.OPEN);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Route s = new Route(t, 0, "a", "b", EventStatus.OPEN, null, null);
        Route su = new Route(t, 1, "b", "a", EventStatus.OPEN, null, null);

        testCRUDImpl(s, su);
    }
}
