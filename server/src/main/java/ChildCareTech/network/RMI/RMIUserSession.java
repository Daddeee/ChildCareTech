package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.*;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.*;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RMIUserSession extends UnicastRemoteObject implements UserSession {
    private User user;
    private MenuController menuController;
    private DishController dishController;
    private CanteenController canteenController;
    private PersonController personController;
    private FoodController foodController;
    private BusController busController;
    private WorkDayController workDayController;
    private KidController kidController;
    private AdultController adultController;
    private PediatristController pediatristController;
    private SupplierController supplierController;
    private StaffController staffController;

    public RMIUserSession(User user) throws RemoteException {
        this.user = user;
        this.menuController = new MenuController();
        this.dishController = new DishController();
        this.canteenController = new CanteenController();
        this.personController = new PersonController();
        this.foodController = new FoodController();
        this.busController = new BusController();
        this.workDayController = new WorkDayController();
        this.kidController = new KidController();
        this.adultController = new AdultController();
        this.pediatristController = new PediatristController();
        this.supplierController = new SupplierController();
        this.staffController = new StaffController();
    }

    @Override
    public List<CanteenDTO> getAllCanteenes() throws RemoteException {
        return canteenController.doGetAllCanteenes();
    }

    @Override
    public void updateAdult(AdultDTO adult) throws RemoteException, UpdateFailedException {
        adultController.doUpdateAdult(adult);
    }

    @Override
    public void updatePediatrist(PediatristDTO pediatristDTO) throws RemoteException, UpdateFailedException {
        pediatristController.doUpdatePediatrist(pediatristDTO);
    }

    @Override
    public void updateStaffMember(StaffDTO staffDTO) throws RemoteException, UpdateFailedException {
        staffController.doUpdateStaffMember(staffDTO);
    }

    @Override
    public void updateSupplier(SupplierDTO supplierDTO) throws RemoteException, UpdateFailedException {
        supplierController.doUpdateSupplier(supplierDTO);
    }

    @Override
    public void addDishToMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        menuController.doAddDishToMenu(menuDTO, dishDTO);
    }

    @Override
    public void removeDishFromMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        menuController.doRemoveDishFromMenu(menuDTO, dishDTO);
    }

    @Override
    public void updateMenu(MealDTO mealDTO) {
        menuController.doUpdateMenu(mealDTO);
    }

    @Override
    public void createMenu(MealDTO mealDTO) {
        menuController.doCreateMenu(mealDTO);
    }

    @Override
    public void deleteDish(DishDTO dishDTO) {
        dishController.doDeleteDish(dishDTO);
    }

    @Override
    public void updateDish(DishDTO dishDTO) {
        dishController.doUpdateDish(dishDTO);
    }



    @Override
    public void createDish(DishDTO dishDTO) {
        dishController.doCreateDish(dishDTO);
    }

    @Override
    public List<DishDTO> getAllDishes() {
        return dishController.doGetAllDishes();
    }

    @Override
    public CanteenDTO getCanteenByName(String name) throws NoSuchElementException {
        return canteenController.doGetCanteenByName(name);
    }

    @Override
    public List<String> getAllCanteenNames() {
        return canteenController.doGetAllCanteenNames();
    }

    @Override
    public void removeCanteen(CanteenDTO canteenDTO) {
        canteenController.doRemoveCanteen(canteenDTO);
    }

    @Override
    public void saveCanteen(CanteenDTO canteenDTO, List<LocalTime> entryTimeList, List<LocalTime> exitTimeList) throws AddFailedException{
        canteenController.doSaveCanteen(canteenDTO, entryTimeList, exitTimeList);
    }

    @Override
    public void removeAllergy(PersonDTO personDTO, FoodDTO foodDTO) {
        personController.doRemoveAllergy(personDTO, foodDTO);
    }

    @Override
    public void addAllergy(PersonDTO personDTO, FoodDTO foodDTO) throws AddFailedException{
        personController.doAddAllergy(personDTO, foodDTO);
    }

    @Override
    public PersonDTO getPerson(String fiscalCode) {
        return personController.doGetPerson(fiscalCode);
    }

    @Override
    public Collection<FoodDTO> getAvailableFoods(PersonDTO personDTO) {
        return foodController.doGetAvailableFoods(personDTO);
    }

    public void updateFood(FoodDTO newFoodDTO) throws UpdateFailedException {
        foodController.doUpdateFood(newFoodDTO);
    }

    public void removeFood(FoodDTO foodDTO) throws RemoteException {
        foodController.doRemoveFood(foodDTO);
    }

    @Override
    public void saveFood(FoodDTO foodDTO) throws AddFailedException {
        foodController.doSaveFood(foodDTO);
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodController.doGetAllFoods();
    }

    @Override
    public void updateRouteEvent(RouteDTO routeDTO) throws RemoteException, UpdateFailedException{
        RouteDAO routeDAO = new RouteDAO();
        EventDAO eventDAO = new EventDAO();
        Route route = DTOEntityAssembler.getEntity(routeDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        routeDAO.setSession(session);
        eventDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(route.getDepartureEvent() != null)
                if(route.getDepartureEvent().getId() == 0) {
                    eventDAO.create(route.getDepartureEvent());
                } else {
                    eventDAO.update(route.getDepartureEvent());
                }

            if(route.getArrivalEvent() != null)
                if(route.getArrivalEvent().getId() == 0) {
                    eventDAO.create(route.getArrivalEvent());
                } else {
                    eventDAO.update(route.getArrivalEvent());
                }

            routeDAO.update(route);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }

        triggerDailyScheduling();
    }

    @Override
    public void removeTripBusRelation(TripDTO tripDTO, BusDTO busDTO) {
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

    @Override
    public void removeTripPartecipation(TripPartecipationDTO tripPartecipationDTO) {
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        TripPartecipation tripPartecipation = DTOEntityAssembler.getEntity(tripPartecipationDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripPartecipationDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            tripPartecipationDAO.delete(tripPartecipation);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public TripDTO getTrip(int id) throws NoSuchElementException {
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

    @Override
    public void saveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws AddFailedException {
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

    @Override
    public void saveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws AddFailedException {
        TripPartecipation tripPartecipation = DTOEntityAssembler.getEntity(tripPartecipationDTO);
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripPartecipationDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            tripPartecipationDAO.create(tripPartecipation);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Set<CheckpointDTO> getEventCheckpoints(EventDTO eventDTO) {
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

    @Override
    public void saveCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time) throws CheckpointFailedException {
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

    @Override
    public LocalDate getMinSavedDate() {
        return workDayController.doGetMinSavedDate();
    }

    @Override
    public LocalDate getMaxSavedDate() {
        return workDayController.doGetMaxSavedDate();
    }

    @Override
    public WorkDayDTO getWorkDay(LocalDate date) {
        return workDayController.doGetWorkDay(date);
    }

    @Override
    public void triggerDailyScheduling() throws RemoteException {
        workDayController.doTriggerDailyScheduling();
    }

    @Override
    public WorkDayDTO getCurrentWorkDay() {
        return DTOFactory.getDTO(RemoteEventObservable.getInstance().getToday());
    }

    @Override
    public void generateDays(DayGenerationSettingsDTO settings) {
        WorkDaysGenerationUtil wdu = new WorkDaysGenerationUtil(settings);
        wdu.generateDays();
    }


    @Override
    public void saveSupplier(SupplierDTO supplierDTO) throws AddFailedException {
        supplierController.doSaveSupplier(supplierDTO);
    }

    @Override
    public void saveStaff(StaffDTO staffDTO) throws AddFailedException {
        staffController.doSaveStaffMember(staffDTO);
    }



    @Override
    public List<AdultDTO> getAllAdultsEx() throws RemoteException {
        return adultController.doGetAllAdultsEx();
    }

    @Override
    public List<StaffDTO> getAllStaffMembers() throws RemoteException {
        return staffController.doGetAllStaffMembers();
    }
    @Override
    public List<SupplierDTO> getAllSuppliers() throws RemoteException {
        return supplierController.doGetAllSuppliers();
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
        } finally {
            session.close();
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

    public List<TripDTO> getAllTrips() {
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

    @Override
    public void updateKid(KidDTO newKidDTO) throws RemoteException, UpdateFailedException {
        kidController.doUpdateKid(newKidDTO);
    }

    public void removeKid(KidDTO kidDTO) throws RemoteException {
        kidController.doRemoveKid(kidDTO);
    }

    @Override
    public void saveKid(KidDTO kidDTO) throws AddFailedException {
        kidController.doSaveKid(kidDTO);
    }


    @Override
    public Collection<KidDTO> getAvailableKids(TripDTO tripDTO) {
        return kidController.doGetAvailableKids(tripDTO);
    }

    @Override
    public List<KidDTO> getAllKids() throws RemoteException {
        return kidController.doGetAllKids();
    }

    @Override
    public void saveAdult(AdultDTO adultDTO) throws AddFailedException {
        adultController.doSaveAdult(adultDTO);
    }

    @Override
    public List<AdultDTO> getAllAdults() throws RemoteException {
        return adultController.doGetAllAdults();
    }

    public void removeAdult(AdultDTO adultDTO) throws RemoteException {
        adultController.doRemoveAdult(adultDTO);
    }

    @Override
    public void savePediatrist(PediatristDTO pediatristDTO) throws AddFailedException {
        pediatristController.doSavePediatrist(pediatristDTO);
    }

    @Override
    public List<PediatristDTO> getAllPediatrists() throws RemoteException {
        return pediatristController.doGetAllPediatrists();
    }

    @Override
    public void removePediatrist(PediatristDTO pediatristDTO) throws RemoteException {
        pediatristController.doRemovePediatrist(pediatristDTO);
    }

    public void removeStaffMember(StaffDTO staffDTO) throws RemoteException {
        staffController.doRemoveStaffMember(staffDTO);
    }

    public void removeSupplier(SupplierDTO supplierDTO) throws RemoteException {
        supplierController.doRemoveSupplier(supplierDTO);
    }
    public void saveBus(BusDTO busDTO) throws RemoteException, AddFailedException{
        busController.doSaveBus(busDTO);
    }

    public void removeBus(BusDTO busDTO) throws RemoteException{
        busController.doRemoveBus(busDTO);
    }

    public void updateBus(BusDTO newBusDTO) throws RemoteException, UpdateFailedException{
        busController.doUpdateBus(newBusDTO);
    }

    @Override
    public List<BusDTO> getAllBuses() throws RemoteException{
        return busController.doGetAllBuses();
    }

    @Override
    public Collection<BusDTO> getAvailableBuses(TripDTO tripDTO) {
        return busController.doGetAvailableBuses(tripDTO);
    }

    @Override
    public boolean isFirstEverStartup() {
        return Boolean.parseBoolean(Settings.getProperty("firstRun"));
    }

    @Override
    public void setFirstEverStartup(boolean value) {
        Settings.storeProperty("firstRun", Boolean.toString(value));
    }

    @Override
    public void logout() throws RemoteException {
        SessionController.removeSession(user.getUserName());
        UnicastRemoteObject.unexportObject(this, true);
    }
}
