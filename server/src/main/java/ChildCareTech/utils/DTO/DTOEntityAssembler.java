package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.DTO.assemblers.MealDTOEntityAssembler;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.assemblers.TripDTOEntityAssembler;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.utils.DTO.assemblers.KidDTOEntityAssembler;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.assemblers.FoodDTOEntityAssembler;
import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.utils.DTO.assemblers.CanteenDTOEntityAssembler;
import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.utils.DTO.assemblers.CheckpointDTOEntityAssembler;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.utils.DTO.assemblers.AdultDTOEntityAssembler;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.assemblers.TripPartecipationDTOEntityAssembler;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.model.entities.Drink;
import ChildCareTech.utils.DTO.assemblers.DrinkDTOEntityAssembler;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.assemblers.WorkDayDTOEntityAssembler;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.utils.DTO.assemblers.RouteDTOEntityAssembler;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.assemblers.PersonDTOEntityAssembler;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.utils.DTO.assemblers.MenuDTOEntityAssembler;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.utils.DTO.assemblers.PediatristDTOEntityAssembler;
import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.utils.DTO.assemblers.BusDTOEntityAssembler;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.DTO.assemblers.SupplyDTOEntityAssembler;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.utils.DTO.assemblers.SupplierDTOEntityAssembler;
import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.utils.DTO.assemblers.DishDTOEntityAssembler;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.assemblers.StaffDTOEntityAssembler;

public class DTOEntityAssembler {
    public static Meal getEntity(MealDTO dto) {
        MealDTOEntityAssembler mealDTOEntityAssembler = new MealDTOEntityAssembler();
        return mealDTOEntityAssembler.assemble(dto);
    }

    public static Trip getEntity(TripDTO dto) {
        TripDTOEntityAssembler tripDTOEntityAssembler = new TripDTOEntityAssembler();
        return tripDTOEntityAssembler.assemble(dto);
    }

    public static Kid getEntity(KidDTO dto) {
        KidDTOEntityAssembler kidDTOEntityAssembler = new KidDTOEntityAssembler();
        return kidDTOEntityAssembler.assemble(dto);
    }

    public static Food getEntity(FoodDTO dto) {
        FoodDTOEntityAssembler foodDTOEntityAssembler = new FoodDTOEntityAssembler();
        return foodDTOEntityAssembler.assemble(dto);
    }

    public static Canteen getEntity(CanteenDTO dto) {
        CanteenDTOEntityAssembler canteenDTOEntityAssembler = new CanteenDTOEntityAssembler();
        return canteenDTOEntityAssembler.assemble(dto);
    }

    public static Checkpoint getEntity(CheckpointDTO dto) {
        CheckpointDTOEntityAssembler checkpointDTOEntityAssembler = new CheckpointDTOEntityAssembler();
        return checkpointDTOEntityAssembler.assemble(dto);
    }

    public static Adult getEntity(AdultDTO dto) {
        AdultDTOEntityAssembler adultDTOEntityAssembler = new AdultDTOEntityAssembler();
        return adultDTOEntityAssembler.assemble(dto);
    }

    public static TripPartecipation getEntity(TripPartecipationDTO dto) {
        TripPartecipationDTOEntityAssembler trippartecipationDTOEntityAssembler = new TripPartecipationDTOEntityAssembler();
        return trippartecipationDTOEntityAssembler.assemble(dto);
    }

    public static Drink getEntity(DrinkDTO dto) {
        DrinkDTOEntityAssembler drinkDTOEntityAssembler = new DrinkDTOEntityAssembler();
        return drinkDTOEntityAssembler.assemble(dto);
    }

    public static WorkDay getEntity(WorkDayDTO dto) {
        WorkDayDTOEntityAssembler workdayDTOEntityAssembler = new WorkDayDTOEntityAssembler();
        return workdayDTOEntityAssembler.assemble(dto);
    }

    public static Route getEntity(RouteDTO dto) {
        RouteDTOEntityAssembler routeDTOEntityAssembler = new RouteDTOEntityAssembler();
        return routeDTOEntityAssembler.assemble(dto);
    }

    public static Person getEntity(PersonDTO dto) {
        PersonDTOEntityAssembler personDTOEntityAssembler = new PersonDTOEntityAssembler();
        return personDTOEntityAssembler.assemble(dto);
    }

    public static Menu getEntity(MenuDTO dto) {
        MenuDTOEntityAssembler menuDTOEntityAssembler = new MenuDTOEntityAssembler();
        return menuDTOEntityAssembler.assemble(dto);
    }

    public static Pediatrist getEntity(PediatristDTO dto) {
        PediatristDTOEntityAssembler pediatristDTOEntityAssembler = new PediatristDTOEntityAssembler();
        return pediatristDTOEntityAssembler.assemble(dto);
    }

    public static Bus getEntity(BusDTO dto) {
        BusDTOEntityAssembler busDTOEntityAssembler = new BusDTOEntityAssembler();
        return busDTOEntityAssembler.assemble(dto);
    }

    public static Supply getEntity(SupplyDTO dto) {
        SupplyDTOEntityAssembler supplyDTOEntityAssembler = new SupplyDTOEntityAssembler();
        return supplyDTOEntityAssembler.assemble(dto);
    }

    public static Supplier getEntity(SupplierDTO dto) {
        SupplierDTOEntityAssembler supplierDTOEntityAssembler = new SupplierDTOEntityAssembler();
        return supplierDTOEntityAssembler.assemble(dto);
    }

    public static Dish getEntity(DishDTO dto) {
        DishDTOEntityAssembler dishDTOEntityAssembler = new DishDTOEntityAssembler();
        return dishDTOEntityAssembler.assemble(dto);
    }

    public static Staff getEntity(StaffDTO dto) {
        StaffDTOEntityAssembler staffDTOEntityAssembler = new StaffDTOEntityAssembler();
        return staffDTOEntityAssembler.assemble(dto);
    }

}