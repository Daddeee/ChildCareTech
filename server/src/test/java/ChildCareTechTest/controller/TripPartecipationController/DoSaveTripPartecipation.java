package ChildCareTechTest.controller.TripPartecipationController;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.TripPartecipationController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link TripPartecipationController#doSaveTripPartecipation(TripPartecipationDTO)}.
 */
public class DoSaveTripPartecipation extends AbstractControllerActionTest {
    private TripPartecipationController tripPartecipationController = new TripPartecipationController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating a TripPartecipation for the same person on one trip.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();
        PersonDAO personDAO = new PersonDAO();
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();

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

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        busDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            tripDAO.create(trip);
            busDAO.create(bus);
            personDAO.create(person);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        }

        PersonDTO personDTO = DTOFactoryFacade.getDTO(person);
        TripDTO tripDTO = DTOFactoryFacade.getDTO(trip);
        BusDTO busDTO = DTOFactoryFacade.getDTO(bus);

        TripPartecipationDTO toBeCreated = new TripPartecipationDTO(0, personDTO, tripDTO, busDTO);
        TripPartecipationDTO toFailCreation = new TripPartecipationDTO(0, personDTO, tripDTO, busDTO);

        try {
            tripPartecipationController.doSaveTripPartecipation(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        tripPartecipationDAO.setSession(session1);

        TripPartecipation original = EntityFactoryFacade.getEntity(toBeCreated);
        TripPartecipation read = tripPartecipationDAO.read("person_id", person.getId()).get(0);
        assertEquals(read, original);

        session1.close();
    }
}
