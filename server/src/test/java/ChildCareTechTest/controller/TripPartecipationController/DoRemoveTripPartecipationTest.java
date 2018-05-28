package ChildCareTechTest.controller.TripPartecipationController;

import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.TripPartecipationController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link TripPartecipationController#doRemoveTripPartecipation(TripPartecipationDTO)} .
 */
public class DoRemoveTripPartecipationTest extends AbstractControllerActionTest {
    private TripPartecipationController tripPartecipationController = new TripPartecipationController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();
        PersonDAO personDAO = new PersonDAO();

        Person person = new Person("0000000000000001", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");

        Bus bus = new Bus("AA111AA", 10);

        Trip trip = new Trip(
                "Cremona",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN
        );
        Route correct1 = new Route( trip, 1, "Piacenza", "Cremona", EventStatus.WAIT, null,null);
        Route correct2 = new Route( trip, 2, "Cremona", "Piacenza", EventStatus.WAIT, null, null);
        trip.setRoutes(new HashSet<>());
        trip.getRoutes().add(correct1);
        trip.getRoutes().add(correct2);

        TripPartecipation tripPartecipation = new TripPartecipation(person, trip, bus);
        TripPartecipationDTO tripPartecipationDTO = DTOFactoryFacade.getDTO(tripPartecipation);

        tripPartecipationController.doRemoveTripPartecipation(tripPartecipationDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripPartecipationDAO.setSession(session);
        personDAO.setSession(session);
        tripDAO.setSession(session);
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            personDAO.create(person);
            busDAO.create(bus);
            tripDAO.create(trip);

            tripPartecipationDAO.create(tripPartecipation);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        tripPartecipationDAO.setSession(session1);
        assertEquals(tripPartecipationDAO.read(tripPartecipation), tripPartecipation);
        session1.close();

        tripPartecipationDTO = DTOFactoryFacade.getDTO(tripPartecipation);
        tripPartecipationController.doRemoveTripPartecipation(tripPartecipationDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        tripPartecipationDAO.setSession(session2);
        assertNull(tripPartecipationDAO.read(tripPartecipation));
        session2.close();

    }
}