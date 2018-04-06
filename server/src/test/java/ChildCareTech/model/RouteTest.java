package ChildCareTech.model;

import ChildCareTech.model.route.Route;
import ChildCareTech.model.route.RouteDAO;
import ChildCareTech.model.trip.Trip;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.fail;

public class RouteTest extends AbstractEntityTest<Route, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new RouteDAO();
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));

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

        Route s = new Route(t, 0, "a", "b");
        Route su = new Route(t, 1, "b", "a");

        testCRUDImpl(s, su);
    }
}
