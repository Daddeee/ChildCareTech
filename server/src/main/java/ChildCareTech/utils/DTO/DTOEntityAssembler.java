package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTO.assemblers.MealDTOEntityAssembler;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.utils.DTO.assemblers.TripDTOEntityAssembler;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.utils.DTO.assemblers.KidDTOEntityAssembler;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.utils.DTO.assemblers.FoodDTOEntityAssembler;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.utils.DTO.assemblers.CanteenDTOEntityAssembler;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.utils.DTO.assemblers.EventDTOEntityAssembler;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.utils.DTO.assemblers.AdultDTOEntityAssembler;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.assemblers.TripPartecipationDTOEntityAssembler;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.model.drink.Drink;
import ChildCareTech.utils.DTO.assemblers.DrinkDTOEntityAssembler;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.assemblers.WorkDayDTOEntityAssembler;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.route.Route;
import ChildCareTech.utils.DTO.assemblers.RouteDTOEntityAssembler;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.person.Person;
import ChildCareTech.utils.DTO.assemblers.PersonDTOEntityAssembler;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.utils.DTO.assemblers.MenuDTOEntityAssembler;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.pediatrist.Pediatrist;
import ChildCareTech.utils.DTO.assemblers.PediatristDTOEntityAssembler;
import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.model.bus.Bus;
import ChildCareTech.utils.DTO.assemblers.BusDTOEntityAssembler;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.assemblers.SupplyDTOEntityAssembler;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.utils.DTO.assemblers.SupplierDTOEntityAssembler;
import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.utils.DTO.assemblers.DishDTOEntityAssembler;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.staff.Staff;
import ChildCareTech.utils.DTO.assemblers.StaffDTOEntityAssembler;

public class DTOEntityAssembler {
    public Meal getEntity(MealDTO dto) {
        MealDTOEntityAssembler mealDTOEntityAssembler = new MealDTOEntityAssembler();
        return mealDTOEntityAssembler.assemble(dto);
    }

    public Trip getEntity(TripDTO dto) {
        TripDTOEntityAssembler tripDTOEntityAssembler = new TripDTOEntityAssembler();
        return tripDTOEntityAssembler.assemble(dto);
    }

    public Kid getEntity(KidDTO dto) {
        KidDTOEntityAssembler kidDTOEntityAssembler = new KidDTOEntityAssembler();
        return kidDTOEntityAssembler.assemble(dto);
    }

    public Food getEntity(FoodDTO dto) {
        FoodDTOEntityAssembler foodDTOEntityAssembler = new FoodDTOEntityAssembler();
        return foodDTOEntityAssembler.assemble(dto);
    }

    public Canteen getEntity(CanteenDTO dto) {
        CanteenDTOEntityAssembler canteenDTOEntityAssembler = new CanteenDTOEntityAssembler();
        return canteenDTOEntityAssembler.assemble(dto);
    }

    public Event getEntity(EventDTO dto) {
        EventDTOEntityAssembler eventDTOEntityAssembler = new EventDTOEntityAssembler();
        return eventDTOEntityAssembler.assemble(dto);
    }

    public Adult getEntity(AdultDTO dto) {
        AdultDTOEntityAssembler adultDTOEntityAssembler = new AdultDTOEntityAssembler();
        return adultDTOEntityAssembler.assemble(dto);
    }

    public TripPartecipation getEntity(TripPartecipationDTO dto) {
        TripPartecipationDTOEntityAssembler trippartecipationDTOEntityAssembler = new TripPartecipationDTOEntityAssembler();
        return trippartecipationDTOEntityAssembler.assemble(dto);
    }

    public Drink getEntity(DrinkDTO dto) {
        DrinkDTOEntityAssembler drinkDTOEntityAssembler = new DrinkDTOEntityAssembler();
        return drinkDTOEntityAssembler.assemble(dto);
    }

    public WorkDay getEntity(WorkDayDTO dto) {
        WorkDayDTOEntityAssembler workdayDTOEntityAssembler = new WorkDayDTOEntityAssembler();
        return workdayDTOEntityAssembler.assemble(dto);
    }

    public Route getEntity(RouteDTO dto) {
        RouteDTOEntityAssembler routeDTOEntityAssembler = new RouteDTOEntityAssembler();
        return routeDTOEntityAssembler.assemble(dto);
    }

    public Person getEntity(PersonDTO dto) {
        PersonDTOEntityAssembler personDTOEntityAssembler = new PersonDTOEntityAssembler();
        return personDTOEntityAssembler.assemble(dto);
    }

    public Menu getEntity(MenuDTO dto) {
        MenuDTOEntityAssembler menuDTOEntityAssembler = new MenuDTOEntityAssembler();
        return menuDTOEntityAssembler.assemble(dto);
    }

    public Pediatrist getEntity(PediatristDTO dto) {
        PediatristDTOEntityAssembler pediatristDTOEntityAssembler = new PediatristDTOEntityAssembler();
        return pediatristDTOEntityAssembler.assemble(dto);
    }

    public Bus getEntity(BusDTO dto) {
        BusDTOEntityAssembler busDTOEntityAssembler = new BusDTOEntityAssembler();
        return busDTOEntityAssembler.assemble(dto);
    }

    public Supply getEntity(SupplyDTO dto) {
        SupplyDTOEntityAssembler supplyDTOEntityAssembler = new SupplyDTOEntityAssembler();
        return supplyDTOEntityAssembler.assemble(dto);
    }

    public Supplier getEntity(SupplierDTO dto) {
        SupplierDTOEntityAssembler supplierDTOEntityAssembler = new SupplierDTOEntityAssembler();
        return supplierDTOEntityAssembler.assemble(dto);
    }

    public Dish getEntity(DishDTO dto) {
        DishDTOEntityAssembler dishDTOEntityAssembler = new DishDTOEntityAssembler();
        return dishDTOEntityAssembler.assemble(dto);
    }

    public Staff getEntity(StaffDTO dto) {
        StaffDTOEntityAssembler staffDTOEntityAssembler = new StaffDTOEntityAssembler();
        return staffDTOEntityAssembler.assemble(dto);
    }

}