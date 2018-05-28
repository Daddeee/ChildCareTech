package ChildCareTechTest.model.DAO;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
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
 * Testing {@link KidDAO#getAvailableKids(Trip)} method in all his limit cases. See method's doc for further specifications.
 */
public class AvailableKidTest {
    private SessionFactory sessionFactory;
    private Session session = null;

    @Test
    public void testAvailableBusQuery(){
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();
        PersonDAO personDAO = new PersonDAO();
        PediatristDAO pediatristDAO = new PediatristDAO();
        KidDAO kidDAO = new KidDAO();

        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();
        Transaction tx = null;

        Trip trip = new Trip(
                "Meta",
                "Gita di test",
                LocalDate.parse("2018-03-01"),
                LocalDate.parse("2018-03-10"),
                EventStatus.OPEN
        );

        Trip overlappingTrip = new Trip(
                "Meta 2",
                "Overlapping trip",
                LocalDate.parse("2018-03-05"),
                LocalDate.parse("2018-03-12"),
                EventStatus.OPEN
        );

        Trip noKidsTrip = new Trip(
                "Meta 3",
                "Gita con tutti i bambini iscritti",
                LocalDate.parse("2018-04-05"),
                LocalDate.parse("2018-04-12"),
                EventStatus.OPEN
        );


        Bus bus = new Bus(
                "AA111AA",
                10
        );

        Bus overlappingBus = new Bus(
                "bb222bb",
                10
        );

        Person adult = new Person(
                "1111222233334444",
                "Tutore",
                "e pediatra",
                LocalDate.now(),
                Sex.MALE,
                "indirizzo",
                "telefono"
        );

        Pediatrist pediatrist = new Pediatrist(adult);

        Person p1 = new Person(
                "1111111111111111",
                "bambino iscritto",
                "cognome",
                LocalDate.now(),
                Sex.MALE,
                "indirizzo",
                "telefono"
        );

        Person p2 = new Person(
                "2222222222222222",
                "bambino non iscritto",
                "cognome",
                LocalDate.now(),
                Sex.MALE,
                "indirizzo",
                "telefono"
        );

        Person p3 = new Person(
                "3333333333333333",
                "bambino overlapping",
                "cognome",
                LocalDate.now(),
                Sex.FEMALE,
                "indirizzo",
                "telefono"
        );

        Kid k1 = new Kid(p1, pediatrist, pediatrist, pediatrist);
        Kid k2 = new Kid(p2, pediatrist, pediatrist, pediatrist);
        Kid k3 = new Kid(p3, pediatrist, pediatrist, pediatrist);

        TripPartecipation tripPartecipation = new TripPartecipation(p1, trip, bus);
        TripPartecipation overlappingTripPartecipation = new TripPartecipation(p3, overlappingTrip, overlappingBus);

        TripPartecipation allKidPart1 = new TripPartecipation(p1, noKidsTrip, overlappingBus);
        TripPartecipation allKidPart2 = new TripPartecipation(p2, noKidsTrip, overlappingBus);
        TripPartecipation allKidPart3 = new TripPartecipation(p3, noKidsTrip, overlappingBus);

        session = sessionFactory.openSession();
        tripPartecipationDAO.setSession(session);
        tripDAO.setSession(session);
        busDAO.setSession(session);
        personDAO.setSession(session);
        pediatristDAO.setSession(session);
        kidDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busDAO.create(bus);
            busDAO.create(overlappingBus);

            tripDAO.create(trip);
            tripDAO.create(overlappingTrip);
            tripDAO.create(noKidsTrip);

            personDAO.create(p1);
            personDAO.create(p2);
            personDAO.create(p3);
            personDAO.create(adult);

            pediatristDAO.create(pediatrist);

            kidDAO.create(k1);
            kidDAO.create(k2);
            kidDAO.create(k3);

            tripPartecipationDAO.create(tripPartecipation);
            tripPartecipationDAO.create(overlappingTripPartecipation);
            tripPartecipationDAO.create(allKidPart1);
            tripPartecipationDAO.create(allKidPart2);
            tripPartecipationDAO.create(allKidPart3);

            List<Kid> availableKids = kidDAO.getAvailableKids(trip);
            assert(availableKids.size() == 1 && availableKids.contains(k2));

            List<Kid> shouldBeEmpty = kidDAO.getAvailableKids(noKidsTrip);
            assert(shouldBeEmpty.isEmpty());

            tx.commit();
        } catch( Exception e){
            e.printStackTrace();
            fail("Errore durante il salvataggio");
        } finally {
            session.close();
        }
    }

    /**
     * Configure and start the testing database.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
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
