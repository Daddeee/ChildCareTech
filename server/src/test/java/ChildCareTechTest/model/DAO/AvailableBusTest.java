package ChildCareTechTest.model.DAO;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Testing {@link BusDAO#getAvailableBuses(Trip)} method and all his limit cases. See method's doc for further specifications.
 */
public class AvailableBusTest {
    private SessionFactory sessionFactory;
    private Session session = null;

    @Test
    public void testAvailableBusQuery(){
        TripDAO tripDAO =new TripDAO();
        BusDAO busDAO = new BusDAO();
        Transaction tx = null;

        Trip queryTrip = new Trip(
                "Meta",
                "Gita di cui cerchiamo i bus disponibili",
                LocalDate.parse("2018-03-01"),
                LocalDate.parse("2018-03-10"),
                EventStatus.OPEN
        );

        Trip overlappingTrip = new Trip(
                "Altra meta",
                "Gita che si sovrappone alla gita di riferimento",
                LocalDate.parse("2018-03-02"),
                LocalDate.parse("2018-03-12"),
                EventStatus.OPEN
        );

        Trip noAvaliableBusesTrip = new Trip(
                "Altra meta",
                "Gita che non si sovrappone a nulla",
                LocalDate.parse("2018-04-02"),
                LocalDate.parse("2018-04-12"),
                EventStatus.OPEN
        );

        Bus availableBus = new Bus(
                "AA111AA",
                10
        );

        Bus sameTripBus = new Bus(
                "BB222BB",
                10
        );

        Bus overlappingBus = new Bus(
                "CC333CC",
                10
        );


        session = sessionFactory.openSession();
        tripDAO.setSession(session);
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busDAO.create(availableBus);
            busDAO.create(sameTripBus);
            busDAO.create(overlappingBus);

            tripDAO.create(queryTrip);
            tripDAO.create(overlappingTrip);
            tripDAO.create(noAvaliableBusesTrip);

            Set<Bus> overlappingBuses = new HashSet<>();
            overlappingBuses.add(overlappingBus);
            overlappingTrip.setBuses(overlappingBuses);

            Set<Bus> queryBuses = new HashSet<>();
            queryBuses.add(sameTripBus);
            queryTrip.setBuses(queryBuses);

            Set<Bus> allBuses = new HashSet<>();
            allBuses.add(overlappingBus);
            allBuses.add(sameTripBus);
            allBuses.add(availableBus);
            noAvaliableBusesTrip.setBuses(allBuses);

            List<Bus> availableBuses = busDAO.getAvailableBuses(queryTrip);
            assert(availableBuses.size() == 1 && availableBuses.contains(availableBus));

            List<Bus> shouldBeEmpty = busDAO.getAvailableBuses(noAvaliableBusesTrip);
            assert (shouldBeEmpty.isEmpty());

            tx.commit();
        } catch( Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Configure and start the testing database.
     * @throws Exception
     */
    @Before
    public void setUp()   {
        // setup the session factory
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    /**
     * After every test, the database is dropped.
     * @throws Exception
     */
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
