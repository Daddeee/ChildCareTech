package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.assemblers.*;

public class DTOEntityAssembler {
    public static Event getEntity(EventDTO dto){
        EventDTOEntityAssembler eventDTOEntityAssembler = new EventDTOEntityAssembler();
        return eventDTOEntityAssembler.assemble(dto);
    }

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