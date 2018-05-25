package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.assemblers.*;

public class EntityFactoryFacade {
    public static Event getEntity(EventDTO dto){
        EventEntityFactoryFacade eventDTOEntityAssembler = new EventEntityFactoryFacade();
        return eventDTOEntityAssembler.assemble(dto);
    }

    public static Meal getEntity(MealDTO dto) {
        MealEntityFactoryFacade mealDTOEntityAssembler = new MealEntityFactoryFacade();
        return mealDTOEntityAssembler.assemble(dto);
    }

    public static Trip getEntity(TripDTO dto) {
        TripEntityFactoryFacade tripDTOEntityAssembler = new TripEntityFactoryFacade();
        return tripDTOEntityAssembler.assemble(dto);
    }

    public static Kid getEntity(KidDTO dto) {
        KidEntityFactoryFacade kidDTOEntityAssembler = new KidEntityFactoryFacade();
        return kidDTOEntityAssembler.assemble(dto);
    }

    public static Food getEntity(FoodDTO dto) {
        FoodEntityFactoryFacade foodDTOEntityAssembler = new FoodEntityFactoryFacade();
        return foodDTOEntityAssembler.assemble(dto);
    }

    public static Canteen getEntity(CanteenDTO dto) {
        CanteenEntityFactoryFacade canteenDTOEntityAssembler = new CanteenEntityFactoryFacade();
        return canteenDTOEntityAssembler.assemble(dto);
    }

    public static Checkpoint getEntity(CheckpointDTO dto) {
        CheckpointEntityFactoryFacade checkpointDTOEntityAssembler = new CheckpointEntityFactoryFacade();
        return checkpointDTOEntityAssembler.assemble(dto);
    }

    public static Adult getEntity(AdultDTO dto) {
        AdultEntityFactoryFacade adultDTOEntityAssembler = new AdultEntityFactoryFacade();
        return adultDTOEntityAssembler.assemble(dto);
    }

    public static TripPartecipation getEntity(TripPartecipationDTO dto) {
        TripPartecipationEntityFactoryFacade trippartecipationDTOEntityAssembler = new TripPartecipationEntityFactoryFacade();
        return trippartecipationDTOEntityAssembler.assemble(dto);
    }

    public static WorkDay getEntity(WorkDayDTO dto) {
        WorkDayEntityFactoryFacade workdayDTOEntityAssembler = new WorkDayEntityFactoryFacade();
        return workdayDTOEntityAssembler.assemble(dto);
    }

    public static Route getEntity(RouteDTO dto) {
        RouteEntityFactoryFacade routeDTOEntityAssembler = new RouteEntityFactoryFacade();
        return routeDTOEntityAssembler.assemble(dto);
    }

    public static Person getEntity(PersonDTO dto) {
        PersonEntityFactoryFacade personDTOEntityAssembler = new PersonEntityFactoryFacade();
        return personDTOEntityAssembler.assemble(dto);
    }

    public static Menu getEntity(MenuDTO dto) {
        MenuEntityFactoryFacade menuDTOEntityAssembler = new MenuEntityFactoryFacade();
        return menuDTOEntityAssembler.assemble(dto);
    }

    public static Pediatrist getEntity(PediatristDTO dto) {
        PediatristEntityFactoryFacade pediatristDTOEntityAssembler = new PediatristEntityFactoryFacade();
        return pediatristDTOEntityAssembler.assemble(dto);
    }

    public static Bus getEntity(BusDTO dto) {
        BusEntityFactoryFacade busDTOEntityAssembler = new BusEntityFactoryFacade();
        return busDTOEntityAssembler.assemble(dto);
    }

    public static Supply getEntity(SupplyDTO dto) {
        SupplyEntityFactoryFacade supplyDTOEntityAssembler = new SupplyEntityFactoryFacade();
        return supplyDTOEntityAssembler.assemble(dto);
    }

    public static Supplier getEntity(SupplierDTO dto) {
        SupplierEntityFactoryFacade supplierDTOEntityAssembler = new SupplierEntityFactoryFacade();
        return supplierDTOEntityAssembler.assemble(dto);
    }

    public static Dish getEntity(DishDTO dto) {
        DishEntityFactoryFacade dishDTOEntityAssembler = new DishEntityFactoryFacade();
        return dishDTOEntityAssembler.assemble(dto);
    }

    public static Staff getEntity(StaffDTO dto) {
        StaffEntityFactoryFacade staffDTOEntityAssembler = new StaffEntityFactoryFacade();
        return staffDTOEntityAssembler.assemble(dto);
    }

}