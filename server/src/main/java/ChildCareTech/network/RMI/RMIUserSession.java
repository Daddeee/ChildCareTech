package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.SessionController;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.DAO.KidDAO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.User;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RMIUserSession extends UnicastRemoteObject implements UserSession {
    private User user;

    public RMIUserSession(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public void logout() throws RemoteException {
        SessionController.removeSession(user.getUserName());
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void saveKid(KidDTO kid) throws RemoteException {
        //
    }

    @Override
    public List<KidDTO> getAllKids() throws RemoteException {
        KidDAO kidDAO = new KidDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<KidDTO> kidDTOList = new ArrayList<>();
        List<Kid> kidList = new ArrayList<>();
        Transaction tx = null;
        kidDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            kidList = kidDAO.readAll();
            for(Kid kid : kidList)
                kidDAO.initializeLazyRelations(kid);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Kid kid : kidList) {
            kidDTOList.add(DTOFactory.getDTO(kid));
        }
        return kidDTOList;
    }

    @Override
    public void removeTrip(TripDTO tripDTO) {
        TripDAO tripDAO = new TripDAO();
        Trip trip;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("meta", tripDTO.getMeta());
        paramMap.put("depDate", tripDTO.getDepDate().toString());
        paramMap.put("arrDate", tripDTO.getArrDate().toString());

        tripDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            trip = tripDAO.read(paramMap).get(0);
            tripDAO.delete(trip);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveTrip(TripDTO tripDTO) throws AddFailedException{
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

    @Override
    public void updateTrip(TripDTO oldTripDTO, TripDTO newTripDTO) throws UpdateFailedException{
        TripDAO tripDAO = new TripDAO();
        Trip newTrip = DTOEntityAssembler.getEntity(newTripDTO);
        Trip oldTrip = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("meta", oldTripDTO.getMeta());
        paramMap.put("depDate", oldTripDTO.getDepDate().toString());
        paramMap.put("arrDate", oldTripDTO.getArrDate().toString());

        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            oldTrip = tripDAO.read(paramMap).get(0);
            newTrip.setPrimaryKey(oldTrip);
            tripDAO.update(oldTrip, newTrip);

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

    public List<TripDTO> getAllTrips() {
        TripDAO dao = new TripDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<TripDTO> tripsDTOCollection = new ArrayList<>();
        List<Trip> tripsCollection = new ArrayList<>();
        Transaction tx = null;

        dao.setSession(session);
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
}
