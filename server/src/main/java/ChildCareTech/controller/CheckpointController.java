package ChildCareTech.controller;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.model.DAO.CheckpointDAO;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class CheckpointController {
    public CheckpointController() {}

    public void doSaveCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time) throws CheckpointFailedException {
        PersonDAO personDAO = new PersonDAO();
        Person person;
        EventDAO eventDAO = new EventDAO();
        Event event;
        Checkpoint record;
        CheckpointDAO checkpointDAO = new CheckpointDAO();

        if(fiscalCode == null || eventDTO == null || time == null)
            throw new CheckpointFailedException("Uno o più parametri mancanti");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        eventDAO.setSession(session);
        checkpointDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            person = personDAO.read(fiscalCode);
            if(person == null)
                throw new CheckpointFailedException("Persona non trovata");

            event = eventDAO.read(eventDTO.getId());
            eventDAO.initializeLazyRelations(event);
            if(event == null || !event.getEventStatus().equals(EventStatus.OPEN))
                throw new CheckpointFailedException("Evento non disponibile");

            record = new Checkpoint(event, person, time, false);
            checkpointDAO.create(record);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new CheckpointFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public Set<CheckpointDTO> doGetEventCheckpoints(EventDTO eventDTO) {
        Event result;
        EventDTO resultDTO = null;
        EventDAO eventDAO = new EventDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        eventDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            result = eventDAO.read(eventDTO.getId());
            eventDAO.initializeLazyRelations(result);
            resultDTO = DTOFactory.getDTO(result);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultDTO == null ? Collections.emptySet() : resultDTO.getCheckpoints();
    }
}
