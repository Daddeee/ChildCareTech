package ChildCareTechTest.controller.CheckpointController;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.Sex;
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
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

public class DoGetEventCheckpointsTest extends AbstractControllerActionTest {
    private CheckpointController controller = new CheckpointController();

    @Override
    public void testAction() {
        EventDAO eventDAO = new EventDAO();
        CheckpointDAO checkpointDAO = new CheckpointDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();
        PersonDAO personDAO = new PersonDAO();

        WorkDay workDay = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.NOON, false);
        Event event = new Event("test", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.OPEN);
        Event wrongEvent = new Event("wrong", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.OPEN);
        Event noCheckpoints = new Event("empty", workDay, LocalTime.NOON, LocalTime.MAX, EventType.DAILY, EventStatus.OPEN);

        Person p1 = new Person("1234567812345678", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("1234567812345688", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");
        Person p3 = new Person("1234567812345888", "nome", "cognome", LocalDate.now(), Sex.MALE, "", "1");

        Checkpoint ok1 = new Checkpoint(event, p1, LocalTime.now());
        Checkpoint ok2 = new Checkpoint(event, p2, LocalTime.now());
        Checkpoint onWrongEvent = new Checkpoint(wrongEvent, p2, LocalTime.now());


        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        eventDAO.setSession(session);
        checkpointDAO.setSession(session);
        workDayDAO.setSession(session);
        personDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            workDayDAO.create(workDay);
            eventDAO.create(event);
            eventDAO.create(wrongEvent);
            personDAO.create(p1);
            personDAO.create(p2);
            personDAO.create(p3);

            checkpointDAO.create(ok1);
            checkpointDAO.create(ok2);
            checkpointDAO.create(onWrongEvent);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        EventDTO eventDTO = DTOFactoryFacade.getDTO(event);
        Set<CheckpointDTO> result = controller.doGetEventCheckpoints(eventDTO);
        assert (result.size() == 2);
        for(CheckpointDTO dto : result){
            Checkpoint c = EntityFactoryFacade.getEntity(dto);
            assert (ok1.equals(c) || ok2.equals(c));
        }
    }
}
