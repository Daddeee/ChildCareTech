package ChildCareTech.utils;

import ChildCareTech.common.DTO.*;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.adult.AdultDTOImpl;
import ChildCareTech.model.bus.Bus;
import ChildCareTech.model.bus.BusDTOImpl;
import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.canteen.CanteenDTOImpl;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.dish.DishDTOImpl;
import ChildCareTech.model.drink.Drink;
import ChildCareTech.model.drink.DrinkDTOImpl;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.event.EventDTOImpl;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.food.FoodDTOImpl;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.kid.KidDTOImpl;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.meal.MealDTOImpl;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.model.menu.MenuDTOImpl;
import ChildCareTech.model.pediatrist.Pediatrist;
import ChildCareTech.model.pediatrist.PediatristDTOImpl;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.model.staff.Staff;
import ChildCareTech.model.staff.StaffDTOImpl;
import ChildCareTech.model.stop.Stop;
import ChildCareTech.model.stop.StopDTOImpl;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.model.supplier.SupplierDTOImpl;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.model.supply.SupplyDTOImpl;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.trip.TripDTOImpl;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.model.trippartecipation.TripPartecipationDTOImpl;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.model.workday.WorkDayDTOImpl;

public class DTOFactory {
    public static AdultDTO getDTO(Adult adult){
        if(adult == null) return null;
        return new AdultDTOImpl(adult);
    }

    public static BusDTO getDTO(Bus bus){
        if(bus == null) return null;
        return new BusDTOImpl(bus);
    }

    public static CanteenDTO getDTO(Canteen canteen){
        if(canteen == null) return null;
        return new CanteenDTOImpl(canteen);
    }

    public static DishDTO getDTO(Dish dish){
        if(dish == null) return null;
        return new DishDTOImpl(dish);
    }

    public static DrinkDTO getDTO(Drink drink){
        if(drink == null) return null;
        return new DrinkDTOImpl(drink);
    }

    public static EventDTO getDTO(Event event){
        if(event == null) return null;
        return new EventDTOImpl(event);
    }

    public static FoodDTO getDTO(Food food){
        if(food == null) return null;
        return new FoodDTOImpl(food);
    }

    public static KidDTO getDTO(Kid kid){
        if(kid == null) return null;
        return new KidDTOImpl(kid);
    }

    public static MealDTO getDTO(Meal meal){
        if(meal == null) return null;
        return new MealDTOImpl(meal);
    }

    public static MenuDTO getDTO(Menu menu){
        if(menu == null) return null;
        return new MenuDTOImpl(menu);
    }

    public static PediatristDTO getDTO(Pediatrist  pediatrist){
        if(pediatrist == null) return null;
        return new PediatristDTOImpl(pediatrist);
    }

    public static PersonDTO getDTO(Person person){
        if(person == null) return null;
        return new PersonDTOImpl(person);
    }

    public static StaffDTO getDTO(Staff staff){
        if(staff == null) return null;
        return new StaffDTOImpl(staff);
    }

    public static StopDTO getDTO(Stop stop){
        if(stop == null) return null;
        return new StopDTOImpl(stop);
    }

    public static SupplierDTO getDTO(Supplier supplier){
        if(supplier == null) return null;
        return new SupplierDTOImpl(supplier);
    }

    public static SupplyDTO getDTO(Supply supply){
        if(supply == null) return null;
        return new SupplyDTOImpl(supply);
    }

    public static TripDTO getDTO(Trip trip){
        if(trip == null) return null;
        return new TripDTOImpl(trip);
    }

    public static TripPartecipationDTO getDTO(TripPartecipation tripPartecipation){
        if(tripPartecipation == null) return null;
        return new TripPartecipationDTOImpl(tripPartecipation);
    }

    public static WorkDayDTO getDTO(WorkDay workDay){
        if(workDay == null) return null;
        return new WorkDayDTOImpl(workDay);
    }
}
