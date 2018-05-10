package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.factories.*;

public class DTOFactory {
    public static EventDTO getDTO(Event event){
        EventDTOFactory factory = new EventDTOFactory();
        return factory.getDTO(event);
    }

    public static MealDTO getDTO(Meal meal) {
        MealDTOFactory factory = new MealDTOFactory();
        return factory.getDTO(meal);
    }

    public static TripDTO getDTO(Trip trip) {
        TripDTOFactory factory = new TripDTOFactory();
        return factory.getDTO(trip);
    }

    public static KidDTO getDTO(Kid kid) {
        KidDTOFactory factory = new KidDTOFactory();
        return factory.getDTO(kid);
    }

    public static FoodDTO getDTO(Food food) {
        FoodDTOFactory factory = new FoodDTOFactory();
        return factory.getDTO(food);
    }

    public static RouteDTO getDTO(Route route) {
        RouteDTOFactory factory = new RouteDTOFactory();
        return factory.getDTO(route);
    }

    public static CanteenDTO getDTO(Canteen canteen) {
        CanteenDTOFactory factory = new CanteenDTOFactory();
        return factory.getDTO(canteen);
    }

    public static CheckpointDTO getDTO(Checkpoint checkpoint) {
        CheckpointDTOFactory factory = new CheckpointDTOFactory();
        return factory.getDTO(checkpoint);
    }

    public static AdultDTO getDTO(Adult adult) {
        AdultDTOFactory factory = new AdultDTOFactory();
        return factory.getDTO(adult);
    }

    public static TripPartecipationDTO getDTO(TripPartecipation trippartecipation) {
        TripPartecipationDTOFactory factory = new TripPartecipationDTOFactory();
        return factory.getDTO(trippartecipation);
    }

    public static WorkDayDTO getDTO(WorkDay workday) {
        WorkDayDTOFactory factory = new WorkDayDTOFactory();
        return factory.getDTO(workday);
    }

    public static PersonDTO getDTO(Person person) {
        PersonDTOFactory factory = new PersonDTOFactory();
        return factory.getDTO(person);
    }

    public static MenuDTO getDTO(Menu menu) {
        MenuDTOFactory factory = new MenuDTOFactory();
        return factory.getDTO(menu);
    }

    public static PediatristDTO getDTO(Pediatrist pediatrist) {
        PediatristDTOFactory factory = new PediatristDTOFactory();
        return factory.getDTO(pediatrist);
    }

    public static BusDTO getDTO(Bus bus) {
        BusDTOFactory factory = new BusDTOFactory();
        return factory.getDTO(bus);
    }

    public static SupplyDTO getDTO(Supply supply) {
        SupplyDTOFactory factory = new SupplyDTOFactory();
        return factory.getDTO(supply);
    }

    public static SupplierDTO getDTO(Supplier supplier) {
        SupplierDTOFactory factory = new SupplierDTOFactory();
        return factory.getDTO(supplier);
    }

    public static DishDTO getDTO(Dish dish) {
        DishDTOFactory factory = new DishDTOFactory();
        return factory.getDTO(dish);
    }

    public static StaffDTO getDTO(Staff staff) {
        StaffDTOFactory factory = new StaffDTOFactory();
        return factory.getDTO(staff);
    }

}