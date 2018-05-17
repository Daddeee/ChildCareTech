package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.*;

public class TripController {
    public TripController() {}

    public List<TripDTO> doGetAllTrips() {
        TripDAO dao = new TripDAO();
        BusDAO busDao = new BusDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<TripDTO> tripsDTOCollection = new ArrayList<>();
        List<Trip> tripsCollection = new ArrayList<>();
        Transaction tx = null;

        dao.setSession(session);
        busDao.setSession(session);
        try{
            tx = session.beginTransaction();

            tripsCollection = dao.readAll();
            for(Trip t : tripsCollection)
                dao.initializeLazyRelations(t);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Trip t : tripsCollection)
            tripsDTOCollection.add(DTOFactory.getDTO(t));

        return tripsDTOCollection;
    }

    public void doUpdateTrip(TripDTO newTripDTO) throws UpdateFailedException {
        TripDAO tripDAO = new TripDAO();
        Trip newTrip = DTOEntityAssembler.getEntity(newTripDTO);
        Trip oldTrip;

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            oldTrip = tripDAO.read(newTripDTO.getId());
            if(!(oldTrip.getDepDate().equals(newTrip.getDepDate()) && oldTrip.getArrDate().equals(newTrip.getArrDate()))
                    && (oldTrip.getBuses() != null && !oldTrip.getBuses().isEmpty()))
                throw new UpdateFailedException("Non è possibile cambiare le date avendo dei bus associati alla gita");

            tripDAO.update(newTrip);

            tx.commit();
        }catch(IndexOutOfBoundsException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException("Non è stata trovata alcuna gita da aggiornare");
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void doSaveTrip(TripDTO tripDTO) throws AddFailedException {
        TripDAO tripDAO = new TripDAO();
        Trip trip = DTOEntityAssembler.getEntity(tripDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();
        StringBuilder fetchErrorMessage = new StringBuilder();

        paramMap.put("meta", tripDTO.getMeta());
        paramMap.put("depDate", tripDTO.getDepDate() == null ? LocalDate.MIN.toString() : tripDTO.getDepDate().toString());
        paramMap.put("arrDate", tripDTO.getArrDate() == null ? LocalDate.MIN.toString() : tripDTO.getArrDate().toString());

        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(tripDAO.read(paramMap).isEmpty())
                tripDAO.create(trip);
            else
                throw new AddFailedException("Una gita per la stessa meta e con stesse date è già presente");

            tx.commit();
        } catch(Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void doRemoveTrip(TripDTO tripDTO) {
        TripDAO tripDAO = new TripDAO();
        Trip trip = DTOEntityAssembler.getEntity(tripDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            tripDAO.delete(trip);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public TripDTO doGetTrip(int id) {
        TripDAO tripDAO = new TripDAO();


        Trip result = null;
        TripDTO resultDTO = null;

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            result = tripDAO.read(id);
            tripDAO.initializeLazyRelations(result);

            tx.commit();
        } catch(Exception e){
            e.printStackTrace();
            throw new NoSuchElementException(e.getMessage());
        } finally {
            session.close();
        }

        resultDTO = DTOFactory.getDTO(result);

        return resultDTO;
    }

    public void doSaveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws AddFailedException {
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();
        Trip trip = null;
        Bus bus = null;

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session);
        busDAO.setSession(session);

        try {
            tx = session.beginTransaction();

            trip = tripDAO.read(tripDTO.getId());
            bus = busDAO.read(busDTO.getId());

            if (trip.getBuses() != null) {
                trip.getBuses().add(bus);
            } else {
                Set<Bus> buses = new HashSet<>();
                buses.add(bus);
                trip.setBuses(buses);
            }

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void doRemoveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) {
        TripDAO tripDAO = new TripDAO();
        BusDAO busDAO = new BusDAO();
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        busDAO.setSession(session);
        tripPartecipationDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            Trip trip = tripDAO.read(tripDTO.getId());
            Bus bus = busDAO.read(busDTO.getId());
            trip.getBuses().removeIf(b -> b.getId() == busDTO.getId());

            tripPartecipationDAO.removeAssociatedTripPartecipations(trip, bus);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
