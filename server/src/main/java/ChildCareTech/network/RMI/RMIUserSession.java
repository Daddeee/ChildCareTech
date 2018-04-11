package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.SessionController;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
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
    public void saveAdult(AdultDTO adultDTO) throws AddFailedException {
        AdultDAO adultDAO = new AdultDAO();
        PersonDAO personDAO = new PersonDAO();
        Adult adult = DTOEntityAssembler.getEntity(adultDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", adultDTO.getPerson().getFiscalCode());

        adultDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                adultDAO.create(adult);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        }
    }


    @Override
    public void saveSupplier(SupplierDTO supplierDTO) throws AddFailedException {
        SupplierDAO supplierDAO = new SupplierDAO();
        PersonDAO personDAO = new PersonDAO();
        Supplier supplier = DTOEntityAssembler.getEntity(supplierDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", supplierDTO.getPerson().getFiscalCode());

        supplierDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                supplierDAO.create(supplier);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        }
    }

    @Override
    public void saveStaff(StaffDTO staffDTO) throws AddFailedException {
        StaffDAO staffDAO = new StaffDAO();
        PersonDAO personDAO = new PersonDAO();
        Staff staff = DTOEntityAssembler.getEntity(staffDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", staffDTO.getPerson().getFiscalCode());

        staffDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                staffDAO.create(staff);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        }
    }

    @Override
    public void savePediatrist(PediatristDTO pediatristDTO) throws AddFailedException {
        PediatristDAO pediatristDAO = new PediatristDAO();
        PersonDAO personDAO = new PersonDAO();
        Pediatrist pediatrist = DTOEntityAssembler.getEntity(pediatristDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", pediatristDTO.getPerson().getFiscalCode());

        pediatristDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                pediatristDAO.create(pediatrist);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        }
    }

    @Override
    public List<AdultDTO> getAllAdults() throws RemoteException {
        AdultDAO adultDAO = new AdultDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<Adult> adultList = new ArrayList<>();
        Transaction tx = null;
        adultDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            adultList = adultDAO.readAll();
            for(Adult adult : adultList)
                adultDAO.initializeLazyRelations(adult);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Adult adult : adultList) {
            adultDTOList.add(DTOFactory.getDTO(adult));
        }
        return adultDTOList;
    }

    @Override
    public void removeTrip(TripDTO tripDTO) {
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
        }
    }

    public void removeRoute(RouteDTO routeDTO) throws RemoteException{
        RouteDAO routeDAO = new RouteDAO();
        Route route = DTOEntityAssembler.getEntity(routeDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        routeDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            routeDAO.delete(route);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
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
    public void updateTrip(TripDTO newTripDTO) throws UpdateFailedException{
        TripDAO tripDAO = new TripDAO();
        Trip newTrip = DTOEntityAssembler.getEntity(newTripDTO);
        Trip oldTrip;

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

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

    public void saveBus(BusDTO bus) throws RemoteException, AddFailedException{
        System.out.println("save bus");
    }

    public void removeBus(BusDTO bus) throws RemoteException{
        System.out.println("remove bus");
    }

    public void updateBus(BusDTO oldBus, BusDTO newBus) throws RemoteException, UpdateFailedException{
        System.out.println("update bus");
    }

    public List<BusDTO> getAllBuses() throws RemoteException{
        ArrayList<BusDTO> a = new ArrayList<>();
        a.add(new BusDTO("prova", null, 20));
        return a;
    }
}
