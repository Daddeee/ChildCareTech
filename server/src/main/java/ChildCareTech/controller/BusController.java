package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BusController {
    public BusController() {}

    public Collection<BusDTO> doGetAvailableBuses(TripDTO tripDTO) {
        BusDAO busDAO = new BusDAO();
        TripDAO tripDAO = new TripDAO();
        List<BusDTO> busesDTOCollection = new ArrayList<>();
        List<Bus> busesCollection = new ArrayList<>();
        Trip trip = DTOEntityAssembler.getEntity(tripDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busesCollection = busDAO.getAvailableBuses(trip);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Bus b : busesCollection)
            busesDTOCollection.add(DTOFactory.getDTO(b));

        return busesDTOCollection;
    }

    public List<BusDTO> doGetAllBuses() {
        BusDAO busDAO = new BusDAO();
        TripDAO tripDAO = new TripDAO();
        List<BusDTO> busesDTOCollection = new ArrayList<>();
        List<Bus> busesCollection = new ArrayList<>();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busesCollection = busDAO.readAll();
            for(Bus b : busesCollection)
                busDAO.initializeLazyRelations(b);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Bus b : busesCollection)
            busesDTOCollection.add(DTOFactory.getDTO(b));

        return busesDTOCollection;
    }

    public void doUpdateBus(BusDTO newBusDTO) throws UpdateFailedException {
        BusDAO busDAO = new BusDAO();
        Bus newBus = DTOEntityAssembler.getEntity(newBusDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busDAO.update(newBus);

            tx.commit();
        }catch(IndexOutOfBoundsException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException("Non è stato trovato alcun autobus da aggiornare");
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void doRemoveBus(BusDTO busDTO) {
        BusDAO busDAO = new BusDAO();
        Bus bus = DTOEntityAssembler.getEntity(busDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            busDAO.delete(bus);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doSaveBus(BusDTO busDTO) throws AddFailedException {
        Bus bus = DTOEntityAssembler.getEntity(busDTO);
        BusDAO busDAO = new BusDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busDAO.create(bus);

            tx.commit();
        } catch(Exception ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
            throw new AddFailedException(ex.getMessage());
        } finally {
            session.close();
        }
    }

}
