package ChildCareTechTest.controller.CheckpointController;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.controller.CheckpointController;
import ChildCareTech.model.DAO.CheckpointDAO;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link CheckpointController#doSaveCheckpoint(String, EventDTO, LocalTime)}.
 */
public class DoSaveCheckpointTest extends AbstractControllerActionTest {
    private CheckpointController controller = new CheckpointController();

    /**
     * Testing normal behavior and limit cases for this method.
     * See {@link ChildCareTech.common.UserSessionFacade#saveCheckpoint(String, EventDTO, LocalTime) the facade documentation} for further details.
     */
    @Override
    public void testAction() {
        EventDAO eventDAO = new EventDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();
        PersonDAO personDAO = new PersonDAO();

        WorkDay workDay = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.NOON, true);
        Event openEvent = new Event("test", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.OPEN);
        Event closedEvent = new Event("test closed", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.CLOSED);
        Event tripEvent = new Event("test trip not supported", workDay, LocalTime.NOON, LocalTime.MAX, EventType.TRIP, EventStatus.OPEN);
        Person person = new Person("1234123412341234", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        eventDAO.setSession(session);
        workDayDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            workDayDAO.create(workDay);
            eventDAO.create(openEvent);
            eventDAO.create(closedEvent);
            eventDAO.create(tripEvent);
            personDAO.create(person);

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
        EventDTO tripEventDTO = DTOFactoryFacade.getDTO(tripEvent);

        try {
            controller.doSaveCheckpoint(person.getFiscalCode(), openEventDTO, LocalTime.now());
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        //Checkpoint già salvato per questa persona in questo evento
        try{
            controller.doSaveCheckpoint(person.getFiscalCode(), openEventDTO, LocalTime.now());
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        try{
            controller.doSaveCheckpoint("Non esiste questo codice fiscale", openEventDTO, LocalTime.now());
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        try{
            controller.doSaveCheckpoint(person.getFiscalCode(), closedEventDTO, LocalTime.now());
            fail("Exception not thrown");
        } catch (CheckpointFailedException e) {}

        try{
            controller.doSaveCheckpoint(person.getFiscalCode(), tripEventDTO, LocalTime.now());
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
