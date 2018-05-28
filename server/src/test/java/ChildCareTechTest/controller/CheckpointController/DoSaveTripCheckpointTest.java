package ChildCareTechTest.controller.CheckpointController;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.controller.CheckpointController;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link CheckpointController#doSaveTripCheckpoint(String, EventDTO, LocalTime, String, TripDTO)}.
 */
public class DoSaveTripCheckpointTest extends AbstractControllerActionTest {
    private CheckpointController controller = new CheckpointController();

    /**
     * Testing normal behavior and limit cases for this method.
     * See {@link ChildCareTech.common.UserSessionFacade#saveTripCheckpoint(String, EventDTO, LocalTime, String, TripDTO) the facade documentation} for further details.
     */
    @Override
    public void testAction() {
        EventDAO eventDAO = new EventDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();
        PersonDAO personDAO = new PersonDAO();
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        WorkDay workDay = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.NOON, true);
        Event openEvent = new Event("test", workDay, LocalTime.NOON, LocalTime.MAX, EventType.TRIP, EventStatus.OPEN);
        Event closedEvent = new Event("test closed", workDay, LocalTime.NOON, LocalTime.MAX, EventType.TRIP, EventStatus.CLOSED);
        Event dailyEvent = new Event("test type not supported", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.OPEN);
        Person person = new Person("1234123412341234", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");
        Person personNotParticipating = new Person("1231231231231234", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");

        Trip trip = new Trip("Cremona", LocalDate.now(), LocalDate.now(), EventStatus.OPEN);
        Bus bus = new Bus("AA111AA", 10);
        Bus wrongBus = new Bus("BB222BB", 10);
        TripPartecipation tripPartecipation = new TripPartecipation(person, trip, bus);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        eventDAO.setSession(session);
        workDayDAO.setSession(session);
        personDAO.setSession(session);
        tripDAO.setSession(session);
        busDAO.setSession(session);
        tripPartecipationDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            workDayDAO.create(workDay);
            eventDAO.create(openEvent);
            eventDAO.create(closedEvent);
            eventDAO.create(dailyEvent);
            personDAO.create(person);
            personDAO.create(personNotParticipating);

            tripDAO.create(trip);
            busDAO.create(bus);
            busDAO.create(wrongBus);
            tripPartecipationDAO.create(tripPartecipation);

            trip.setBuses(new HashSet<>());
            trip.getBuses().add(bus);
            trip.getBuses().add(wrongBus);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        EventDTO openEventDTO = DTOFactoryFacade.getDTO(openEvent);
        EventDTO closedEventDTO = DTOFactoryFacade.getDTO(closedEvent);
        EventDTO unsupportedEventDTO = DTOFactoryFacade.getDTO(dailyEvent);

        TripDTO tripDTO = DTOFactoryFacade.getDTO(trip);

        try {
            controller.doSaveTripCheckpoint(person.getFiscalCode(), openEventDTO, LocalTime.now(), bus.getLicensePlate(), tripDTO);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        //Checkpoint già salvato per questa persona in questo evento
        try{
            controller.doSaveTripCheckpoint(person.getFiscalCode(), openEventDTO, LocalTime.now(), bus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        //Persona che non partecipa alla gita
        try{
            controller.doSaveTripCheckpoint(personNotParticipating.getFiscalCode(), openEventDTO, LocalTime.now(), bus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        //Bus sbagliato
        try{
            controller.doSaveTripCheckpoint(person.getFiscalCode(), openEventDTO, LocalTime.now(), wrongBus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        //Persona inesistente
        try{
            controller.doSaveTripCheckpoint("Non esiste questo codice fiscale", openEventDTO, LocalTime.now(), wrongBus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        //Evento chiuso
        try{
            controller.doSaveTripCheckpoint(person.getFiscalCode(), closedEventDTO, LocalTime.now(), bus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        //Evento non di tipo gita
        try{
            controller.doSaveTripCheckpoint(person.getFiscalCode(), unsupportedEventDTO, LocalTime.now(), bus.getLicensePlate(), tripDTO);
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}


        CheckpointDAO checkpointDAO = new CheckpointDAO();
        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        checkpointDAO.setSession(session2);
        Checkpoint read = checkpointDAO.read("person_id", person.getId()).get(0);
        assertEquals(read.getEvent(), openEvent);

        session2.close();
    }
}