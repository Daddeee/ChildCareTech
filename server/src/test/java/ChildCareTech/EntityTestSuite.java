package ChildCareTech;

import ChildCareTech.model.entities.*;
import ChildCareTech.model.entities.RouteTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdultTest.class,
        BusTest.class,
        CanteenTest.class,
        DishTest.class,
        CheckpointTest.class,
        FoodTest.class,
        KidTest.class,
        MealTest.class,
        MenuTest.class,
        PediatristTest.class,
        PersonTest.class,
        StaffTest.class,
        RouteTest.class,
        SupplierTest.class,
        SupplyTest.class,
        TripPartecipationTest.class,
        TripTest.class,
        WorkDayTest.class
})

public class EntityTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}