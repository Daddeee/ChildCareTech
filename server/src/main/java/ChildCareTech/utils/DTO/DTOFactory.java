package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTO.factories.MealDTOFactory;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.utils.DTO.factories.TripDTOFactory;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.factories.KidDTOFactory;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.utils.DTO.factories.FoodDTOFactory;
import ChildCareTech.common.DTO.StopDTO;
import ChildCareTech.model.stop.Stop;
import ChildCareTech.utils.DTO.factories.StopDTOFactory;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.utils.DTO.factories.CanteenDTOFactory;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.utils.DTO.factories.EventDTOFactory;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.utils.DTO.factories.AdultDTOFactory;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.factories.TripPartecipationDTOFactory;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.model.drink.Drink;
import ChildCareTech.utils.DTO.factories.DrinkDTOFactory;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.factories.WorkDayDTOFactory;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.person.Person;
import ChildCareTech.utils.DTO.factories.PersonDTOFactory;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.utils.DTO.factories.MenuDTOFactory;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.pediatrist.Pediatrist;
import ChildCareTech.utils.DTO.factories.PediatristDTOFactory;
import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.model.bus.Bus;
import ChildCareTech.utils.DTO.factories.BusDTOFactory;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.factories.SupplyDTOFactory;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.utils.DTO.factories.SupplierDTOFactory;
import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.utils.DTO.factories.DishDTOFactory;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.staff.Staff;
import ChildCareTech.utils.DTO.factories.StaffDTOFactory;

public class DTOFactory {
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

    public static StopDTO getDTO(Stop stop) {
        StopDTOFactory factory = new StopDTOFactory();
        return factory.getDTO(stop);
    }

    public static CanteenDTO getDTO(Canteen canteen) {
        CanteenDTOFactory factory = new CanteenDTOFactory();
        return factory.getDTO(canteen);
    }

    public static EventDTO getDTO(Event event) {
        EventDTOFactory factory = new EventDTOFactory();
        return factory.getDTO(event);
    }

    public static AdultDTO getDTO(Adult adult) {
        AdultDTOFactory factory = new AdultDTOFactory();
        return factory.getDTO(adult);
    }

    public static TripPartecipationDTO getDTO(TripPartecipation trippartecipation) {
        TripPartecipationDTOFactory factory = new TripPartecipationDTOFactory();
        return factory.getDTO(trippartecipation);
    }

    public static DrinkDTO getDTO(Drink drink) {
        DrinkDTOFactory factory = new DrinkDTOFactory();
        return factory.getDTO(drink);
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