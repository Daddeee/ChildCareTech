package ChildCareTech.common;

import ChildCareTech.common.DTO.*;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Defines all the operations a client can perform on the server through his session.
 */
public interface UserSessionFacade extends Remote {

    /**
     * Save the provided kid in the database.
     *
     * @param kid the Data Transfer Object holding the new kid's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveKid(KidDTO kid) throws RemoteException, AddFailedException;

    /**
     * Save the provided Adult in the database.
     *
     * @param adult the Data Transfer Object holding the new adult's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveAdult(AdultDTO adult) throws  RemoteException, AddFailedException;
    /**
     * Save the provided supplier in the database.
     *
     * @param supplier the Data Transfer Object holding the new supplier's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveSupplier(SupplierDTO supplier) throws RemoteException, AddFailedException;

    /**
     * Save the provided pediatrist in the database.
     *
     * @param pediatrist the Data Transfer Object holding the new pediatrist's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void savePediatrist(PediatristDTO pediatrist) throws RemoteException, AddFailedException;

    /**
     * Save the provided staff member in the database.
     *
     * @param staff the Data Transfer Object holding the new staff member's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveStaff(StaffDTO staff) throws  RemoteException, AddFailedException;

    /**
     * Remove the provided kid from the database if present.
     *
     * @param kid the Data Transfer Object holding the kid's data.
     * @throws RemoteException
     */
    void removeKid(KidDTO kid) throws RemoteException;

    /**
     * Remove the provided adult from the database if present.
     *
     * @param adult the Data Transfer Object holding the adult's data.
     * @throws RemoteException
     */
    void removeAdult(AdultDTO adult) throws RemoteException;

    /**
     * Remove the provided pediatrist from the database if present.
     *
     * @param pediatrist the Data Transfer Object holding the pediatrist's data.
     * @throws RemoteException
     */
    void removePediatrist(PediatristDTO pediatrist) throws RemoteException;

    /**
     * Remove the provided staff member from the database if present.
     *
     * @param staff the Data Transfer Object holding the staff member's data.
     * @throws RemoteException
     */
    void removeStaff(StaffDTO staff) throws RemoteException;

    /**
     * Remove the provided supplier from the database if present.
     *
     * @param supplier the Data Transfer Object holding the supplier's data.
     * @throws RemoteException
     */
    void removeSupplier(SupplierDTO supplier) throws RemoteException;

    /**
     * Update the kid matching the identifier with the new data provided in the DTO.
     *
     * @param newKid the Data Transfer Object holding the new kid's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateKid(KidDTO newKid) throws RemoteException, UpdateFailedException;

    /**
     * Update the adult matching the identifier with the new data provided in the DTO.
     *
     * @param newAdult the Data Transfer Object holding the new adult's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateAdult(AdultDTO newAdult) throws RemoteException, UpdateFailedException;

    /**
     * Update the pediatrist matching the identifier with the new data provided in the DTO.
     *
     * @param newPediatrist the Data Transfer Object holding the new pediatrist's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updatePediatrist(PediatristDTO newPediatrist) throws RemoteException, UpdateFailedException;

    /**
     * Update the staff member matching the identifier with the new data provided in the DTO.
     *
     * @param newStaff the Data Transfer Object holding the new staff member's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateStaffMember(StaffDTO newStaff) throws RemoteException, UpdateFailedException;

    /**
     * Update the supplier matching the identifier with the new data provided in the DTO.
     *
     * @param newSupplier the Data Transfer Object holding the new supplier's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateSupplier(SupplierDTO newSupplier) throws RemoteException, UpdateFailedException;

    /**
     * @return a List of DTO containing data from all kids saved in the database.
     * @throws RemoteException
     */
    List<KidDTO> getAllKids() throws RemoteException;

    /**
     * Get all kids that can partecipate in the provided trip.
     * For a kid to be available, it must not be already associated to a trip that
     * overlaps temporally with the provided trip.
     *
     * @param trip the DTO containing data of the trip for which kids are searched.
     * @return a List containing DTO of all available kid entities.
     */
    Collection<KidDTO> getAvailableKids(TripDTO trip) throws RemoteException;

    /**
     * @return a List of DTO containing data from all adults saved in the database.
     * @throws RemoteException
     */
    List<AdultDTO> getAllAdults() throws RemoteException;

    /**
     * @return a List of DTO containing data from all adults saved in the database that are not staff members, suppliers or pediatrists.
     * @throws RemoteException
     */
    List<AdultDTO> getAllAdultsExclusive() throws RemoteException;

    /**
     * @return a List of DTO containing data from all pediatrists saved in the database.
     * @throws RemoteException
     */
    List<PediatristDTO> getAllPediatrists() throws RemoteException;

    /**
     * @return a List of DTO containing data from all staff members saved in the database.
     * @throws RemoteException
     */
    List<StaffDTO> getAllStaff() throws RemoteException;

    /**
     * @return a List of DTO containing data from all suppliers saved in the database.
     * @throws RemoteException
     */
    List<SupplierDTO> getAllSuppliers() throws RemoteException;

    /**
     * Set the provided adult as a contact for the provided kid.
     *
     * @param kidDTO the DTO containing all the data of the Kid.
     * @param adultDTO the DTO containing all the data of the Adult.
     * @throws RemoteException
     */
    void addContactToKid(KidDTO kidDTO, AdultDTO adultDTO) throws RemoteException;

    /**
     * Remove the provided adult from the contacts of the provided kid.
     *
     * @param kidDTO the DTO containing all the data of the Kid.
     * @param adultDTO the DTO containing all the data of the Adult.
     * @throws RemoteException
     */
    void removeContactFromKid(KidDTO kidDTO, AdultDTO adultDTO) throws RemoteException;

    /**
     * Save the provided trip in the database.
     *
     * @param trip the Data Transfer Object holding the new trip's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveTrip(TripDTO trip) throws RemoteException, AddFailedException;

    /**
     * Update the trip matching the identifier with the new data provided in the DTO.
     *
     * @param newTrip the Data Transfer Object holding the new trip's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateTrip(TripDTO newTrip) throws RemoteException, UpdateFailedException;

    /**
     * @param id the id of the searched trip
     * @return the DTO containing all the data of the trip entity matching the provided id.
     * @throws NoSuchElementException if no element is found.
     * @throws RemoteException
     */
    TripDTO getTrip(int id) throws RemoteException, NoSuchElementException;

    /**
     * Remove the provided trip from the database if present.
     *
     * @param trip the Data Transfer Object holding the trip's data.
     * @throws RemoteException
     */
    void removeTrip(TripDTO trip) throws RemoteException;

    /**
     * @return a List of DTO containing data from all trips saved in the database.
     * @throws RemoteException
     */
    List<TripDTO> getAllTrips() throws RemoteException;

    /**
     * Remove the provided route from the database if present.
     *
     * @param route the Data Transfer Object holding the trip's data.
     * @throws RemoteException
     */
    void removeRoute(RouteDTO route) throws RemoteException;

    /**
     * Persist the event data contained in the provided route. If some event are missing they are automatically created.
     *
     * @param routeDTO the DTO holding the new event data.
     * @throws UpdateFailedException if event updating goes wrong.
     * @throws RemoteException
     */
    void updateRouteEvent(RouteDTO routeDTO) throws RemoteException, UpdateFailedException;

    /**
     * Save the provided trip partecipation in the database.
     *
     * @param tripPartecipation the Data Transfer Object holding the new trip partecipation's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveTripPartecipation(TripPartecipationDTO tripPartecipation) throws RemoteException, AddFailedException;

    /**
     * Remove the provided trip partecipation from the database if present.
     *
     * @param tripPartecipation the Data Transfer Object holding the trip partecipation's data.
     * @throws RemoteException
     */
    void removeTripPartecipation(TripPartecipationDTO tripPartecipation) throws RemoteException;

    /**
     * Save the association between the provided trip and the provided bus that will be used in it.
     *
     * @param tripDTO the Data Transfer Object holding the trip's data.
     * @param busDTO the Data Transfer Object holding the bus data.
     * @throws RemoteException
     * @throws AddFailedException
     */
    void saveTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws RemoteException, AddFailedException;

    /**
     * Remove the association between the provided trip and the provided bus.
     *
     * @param tripDTO the Data Transfer Object holding the trip's data.
     * @param busDTO the Data Transfer Object holding the bus data.
     * @throws RemoteException
     */
    void removeTripBusRelation(TripDTO tripDTO, BusDTO busDTO) throws RemoteException;

    /**
     * Save the provided bus in the database.
     *
     * @param bus the Data Transfer Object holding the new bus data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveBus(BusDTO bus) throws RemoteException, AddFailedException;

    /**
     * Update the bus matching the identifier with the new data provided in the DTO.
     *
     * @param newBus the Data Transfer Object holding the new bus data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateBus(BusDTO newBus) throws RemoteException, UpdateFailedException;

    /**
     * Remove the provided bus from the database if present.
     *
     * @param bus the Data Transfer Object holding the bus data.
     * @throws RemoteException
     */
    void removeBus(BusDTO bus) throws RemoteException;

    /**
     * @return a List of DTO containing data from all buses saved in the database.
     * @throws RemoteException
     */
    List<BusDTO> getAllBuses() throws RemoteException;

    /**
     * Get all buses that are available for use in the provided trip.
     * For a bus to be available, it must not be already associated to a trip that
     * overlaps temporally with the provided trip.
     *
     * @param trip the DTO containing data of the trip for which buses are searched.
     * @return a List containing all available bus entities.
     */
    Collection<BusDTO> getAvailableBuses(TripDTO trip) throws RemoteException;

    /**
     * Save the provided food in the database.
     *
     * @param food the Data Transfer Object holding the new food's data.
     * @throws AddFailedException if entity saving goes wrong.
     * @throws RemoteException
     */
    void saveFood(FoodDTO food) throws RemoteException, AddFailedException;

    /**
     * Update the food matching the identifier with the new data provided in the DTO.
     *
     * @param newFood the Data Transfer Object holding the new food's data and the identifier.
     * @throws UpdateFailedException if entity updating goes wrong.
     * @throws RemoteException
     */
    void updateFood(FoodDTO newFood) throws RemoteException, UpdateFailedException;

    /**
     * Remove the provided food from the database if present.
     *
     * @param food the Data Transfer Object holding the food's data.
     * @throws RemoteException
     */
    void removeFood(FoodDTO food) throws RemoteException;

    /**
     * @return a List of DTO containing data from all foods saved in the database.
     * @throws RemoteException
     */
    List<FoodDTO> getAllFoods() throws RemoteException;

    /**
     * Get all foods to which a person is not yet allergic.
     *
     * @param person DTO containing data of the person for which foods are searched.
     * @return a List containing all foods entities except the ones to which the person is already allergic.
     */
    Collection<FoodDTO> getAvailableFoods(PersonDTO person) throws RemoteException;

    /**
     * Add food to the person's allergies.
     *
     * @param person the provided person.
     * @param food the provided food.
     * @throws AddFailedException if addition goes wrong.
     * @throws RemoteException
     */
    void addAllergy(PersonDTO person, FoodDTO food) throws RemoteException, AddFailedException;

    /**
     * Remove food from the person's allergies.
     *
     * @param person the provided person.
     * @param food the provided food.
     * @throws RemoteException
     */
    void removeAllergy(PersonDTO person, FoodDTO food) throws RemoteException;

    /**
     * @param fiscalCode the fiscal code of the searched person.
     * @return the DTO containing all the data of the person entity matching the provided fiscal code.
     * @throws RemoteException
     */
    PersonDTO getPerson(String fiscalCode) throws RemoteException;

    /**
     * @return true if this is the first time the server is running, false otherwise.
     * @throws RemoteException
     */
    boolean isFirstEverStartup() throws RemoteException;

    /**
     * Set the config that states that this is the first run of the server.
     *
     * @param value
     * @throws RemoteException
     */
    void setFirstEverStartup(boolean value) throws RemoteException;

    /**
     * Generate WorkDays based on the provided settings, starting from today.
     *
     * @param settings setting related to work day generation.
     * @throws RemoteException
     */
    void generateDays(DayGenerationSettingsDTO settings) throws RemoteException;

    /**
     * Rerun today 's daily scheduling.
     * @throws RemoteException
     */
    void triggerDailyScheduling() throws RemoteException;

    /**
     * Save the provided canteen in the database and then generate associated meals.
     * <p>
     * For each WorkDay present in the database k meals (and respective menus) are created, where k is the size of the entry time list
     * (exit time list has the same size). Each meal starts and ends at times specified in both lists.
     *
     * @param canteenDTO the DTO containing the new canteen's data.
     * @param entryTimeList the list of beginning times of each daily meal.
     * @param exitTimeList the list of ending times of each daily meal.
     * @throws AddFailedException if saving goes wrong.
     * @throws RemoteException
     */
    void saveCanteen(CanteenDTO canteenDTO, List<LocalTime> entryTimeList, List<LocalTime> exitTimeList) throws RemoteException, AddFailedException;

    /**
     * @return a List of strings containing the name of all canteens saved in the database.
     * @throws RemoteException
     */
    List<String> getAllCanteenNames() throws RemoteException;

    /**
     * @return a list of DTO containing the data of all canteens saved in the database.
     * @throws RemoteException
     */
    List<CanteenDTO> getAllCanteenes() throws RemoteException;

    /**
     * @param name the searched name
     * @return a DTO containing data of the canteen with the given name.
     * @throws NoSuchElementException if no element is found with the given name.
     * @throws RemoteException
     */
    CanteenDTO getCanteenByName(String name) throws NoSuchElementException, RemoteException;

    /**
     * Remove the provided canteen from the database if present.
     *
     * @param canteen the Data Transfer Object holding the canteen's data.
     * @throws RemoteException
     */
    void removeCanteen(CanteenDTO canteen) throws RemoteException;

    /**
     * Save the provided dish in the database.
     *
     * @param dish the Data Transfer Object holding the new trip's data.
     * @throws RemoteException
     */
    void createDish(DishDTO dish) throws RemoteException;

    /**
     * Update the dish matching the identifier with the new data provided in the DTO.
     *
     * @param newDish the Data Transfer Object holding the new dish data and the identifier.
     * @throws RemoteException
     */
    void updateDish(DishDTO newDish) throws RemoteException;

    /**
     * @return a list of DTO containing the data of all dishes saved in the database.
     * @throws RemoteException
     */
    List<DishDTO> getAllDishes() throws RemoteException;

    /**
     * Remove the provided dish from the database if present.
     *
     * @param dish a Data Transfer Object holding the dish data.
     * @throws RemoteException
     */
    void deleteDish(DishDTO dish) throws RemoteException;

    /**
     * Create a menu associated to the given meal.
     *
     * @param mealDTO the meal to which associate the created menu.
     * @throws RemoteException
     */
    void createMenu(MealDTO mealDTO) throws RemoteException;

    /**
     * Update the menu associated to the given meal with the data contained in the given DTO.
     *
     * @param meal the Data Transfer Object holding the meal's and the new menu's data.
     * @throws RemoteException
     */
    void updateMenu(MealDTO meal) throws RemoteException;

    /**
     * Validate a menu, signaling if there are kids that can't eat at least 2 dishes of the given menu because of allergies.
     *
     * @param menuDTO the menu to be validated.
     * @throws UpdateFailedException if there are kids without sufficient dishes available.
     * @throws RemoteException
     */
    void validateMenu(MenuDTO menuDTO) throws RemoteException, UpdateFailedException;

    /**
     * Add the provided dish from the given menu.
     *
     * @param menu the DTO containing all data of the menu.
     * @param dish the DTO containing all data of the dish.
     * @throws RemoteException
     */
    void addDishToMenu(MenuDTO menu, DishDTO dish) throws RemoteException;

    /**
     * Remove the provided dish from the given menu.
     *
     * @param menu the DTO containing all data of the menu.
     * @param dish the DTO containing all data of the dish.
     * @throws RemoteException
     */
    void removeDishFromMenu(MenuDTO menu, DishDTO dish) throws RemoteException;

    /**
     * @return the WorkDay whose events are current scheduled in the server.
     * @throws RemoteException
     */
    WorkDayDTO getCurrentWorkDay() throws RemoteException;

    /**
     * @param date the searched date.
     * @return the WorkDay that has the given date.
     * @throws RemoteException
     */
    WorkDayDTO getWorkDay(LocalDate date) throws RemoteException;

    /**
     * @return the lowest date saved in the WorkDay table.
     * @throws RemoteException
     */
    LocalDate getMinSavedDate() throws RemoteException;

    /**
     * @return the greatest date saved in the WorkDay table.
     * @throws RemoteException
     */
    LocalDate getMaxSavedDate() throws RemoteException;

    /**
     * @param eventDTO the event of which checkpoints are returned.
     * @return a Set containing all the checkpoints recorded on the given event.
     * @throws RemoteException
     */
    Set<CheckpointDTO> getEventCheckpoints(EventDTO eventDTO) throws RemoteException;

    /**
     * Save in the database a checkpoint for the given person, on a generic event and at the time provided as attribute.
     *
     * @param fiscalCode fiscal code of the person that recorded on the event.
     * @param event the event on which the person recorded his presence.
     * @param time the time of recording.
     * @throws CheckpointFailedException if the saving goes wrong.
     * @throws RemoteException
     */
    void saveCheckpoint(String fiscalCode, EventDTO event, LocalTime time) throws CheckpointFailedException, RemoteException;

    /**
     * Save in the database a checkpoint for the given person, on a trip event and at the time provided as attribute.
     * <p>
     * Unlike {@link #saveCheckpoint(String, EventDTO, LocalTime) normal checkpoint saving}, this one performs a control making
     * sure that exists a trip partecipation for the given person, on the given bus and for the provided trip before saving the
     * checkpoint.
     *
     * @param fiscalCode fiscal code of the person that recorded on the event.
     * @param event the event on which the person recorded his presence.
     * @param time the time of recording.
     * @param busPlate the license plate of the bus on which the person is travelling.
     * @param trip the trip on which the event is occurring.
     * @throws CheckpointFailedException if the saving goes wrong or no trip partecipation corresponding is found.
     * @throws RemoteException
     */
    void saveTripCheckpoint(String fiscalCode, EventDTO event, LocalTime time, String busPlate, TripDTO trip) throws CheckpointFailedException, RemoteException;

    /**
     * Register a remote event observer on the server. The observer will be used to receive and handle update requests from the server.
     *
     * @param observer the observer that will be recorded.
     * @throws RemoteException
     */
    void addRemoteEventObserver(RemoteEventObserver observer) throws RemoteException;

    /**
     * Remove the given remote event observer from the server.
     *
     * @param observer the observer that will be removed.
     * @throws RemoteException
     */
    void removeRemoteEventObserver(RemoteEventObserver observer) throws RemoteException;

    /**
     * Close the session and log out the current User.
     * @throws RemoteException
     */
    void logout() throws RemoteException;
}
