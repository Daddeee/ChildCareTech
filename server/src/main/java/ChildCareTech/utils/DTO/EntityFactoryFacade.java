package ChildCareTech.utils.DTO;

import ChildCareTech.common.DTO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.assemblers.*;

public class EntityFactoryFacade {
    public static Event getEntity(EventDTO dto){
        EventEntityFactory eventDTOEntityAssembler = new EventEntityFactory();
        return eventDTOEntityAssembler.assemble(dto);
    }

    public static Meal getEntity(MealDTO dto) {
        MealEntityFactory mealDTOEntityAssembler = new MealEntityFactory();
        return mealDTOEntityAssembler.assemble(dto);
    }

    public static Trip getEntity(TripDTO dto) {
        TripEntityFactory tripDTOEntityAssembler = new TripEntityFactory();
        return tripDTOEntityAssembler.assemble(dto);
    }

    public static Kid getEntity(KidDTO dto) {
        KidEntityFactory kidDTOEntityAssembler = new KidEntityFactory();
        return kidDTOEntityAssembler.assemble(dto);
    }

    public static Food getEntity(FoodDTO dto) {
        FoodEntityFactory foodDTOEntityAssembler = new FoodEntityFactory();
        return foodDTOEntityAssembler.assemble(dto);
    }

    public static Canteen getEntity(CanteenDTO dto) {
        CanteenEntityFactory canteenDTOEntityAssembler = new CanteenEntityFactory();
        return canteenDTOEntityAssembler.assemble(dto);
    }

    public static Checkpoint getEntity(CheckpointDTO dto) {
        CheckpointEntityFactory checkpointDTOEntityAssembler = new CheckpointEntityFactory();
        return checkpointDTOEntityAssembler.assemble(dto);
    }

    public static Adult getEntity(AdultDTO dto) {
        AdultEntityFactory adultDTOEntityAssembler = new AdultEntityFactory();
        return adultDTOEntityAssembler.assemble(dto);
    }

    public static TripPartecipation getEntity(TripPartecipationDTO dto) {
        TripPartecipationEntityFactory trippartecipationDTOEntityAssembler = new TripPartecipationEntityFactory();
        return trippartecipationDTOEntityAssembler.assemble(dto);
    }

    public static WorkDay getEntity(WorkDayDTO dto) {
        WorkDayEntityFactory workdayDTOEntityAssembler = new WorkDayEntityFactory();
        return workdayDTOEntityAssembler.assemble(dto);
    }

    public static Route getEntity(RouteDTO dto) {
        RouteEntityFactory routeDTOEntityAssembler = new RouteEntityFactory();
        return routeDTOEntityAssembler.assemble(dto);
    }

    public static Person getEntity(PersonDTO dto) {
        PersonEntityFactory personDTOEntityAssembler = new PersonEntityFactory();
        return personDTOEntityAssembler.assemble(dto);
    }

    public static Menu getEntity(MenuDTO dto) {
        MenuEntityFactory menuDTOEntityAssembler = new MenuEntityFactory();
        return menuDTOEntityAssembler.assemble(dto);
    }

    public static Pediatrist getEntity(PediatristDTO dto) {
        PediatristEntityFactory pediatristDTOEntityAssembler = new PediatristEntityFactory();
        return pediatristDTOEntityAssembler.assemble(dto);
    }

    public static Bus getEntity(BusDTO dto) {
        BusEntityFactory busDTOEntityAssembler = new BusEntityFactory();
        return busDTOEntityAssembler.assemble(dto);
    }

    public static Supply getEntity(SupplyDTO dto) {
        SupplyEntityFactory supplyDTOEntityAssembler = new SupplyEntityFactory();
        return supplyDTOEntityAssembler.assemble(dto);
    }

    public static Supplier getEntity(SupplierDTO dto) {
        SupplierEntityFactory supplierDTOEntityAssembler = new SupplierEntityFactory();
        return supplierDTOEntityAssembler.assemble(dto);
    }

    public static Dish getEntity(DishDTO dto) {
        DishEntityFactory dishDTOEntityAssembler = new DishEntityFactory();
        return dishDTOEntityAssembler.assemble(dto);
    }

    public static Staff getEntity(StaffDTO dto) {
        StaffEntityFactory staffDTOEntityAssembler = new StaffEntityFactory();
        return staffDTOEntityAssembler.assemble(dto);
    }

}