package ChildCareTech;

import ChildCareTech.model.CheckpointTest;
import ChildCareTech.model.RouteTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChildCareTech.model.AdultTest.class,
        ChildCareTech.model.BusTest.class,
        ChildCareTech.model.CanteenTest.class,
        ChildCareTech.model.DishTest.class,
        CheckpointTest.class,
        ChildCareTech.model.FoodTest.class,
        ChildCareTech.model.KidTest.class,
        ChildCareTech.model.MealTest.class,
        ChildCareTech.model.MenuTest.class,
        ChildCareTech.model.PediatristTest.class,
        ChildCareTech.model.PersonTest.class,
        ChildCareTech.model.StaffTest.class,
        RouteTest.class,
        ChildCareTech.model.SupplierTest.class,
        ChildCareTech.model.SupplyTest.class,
        ChildCareTech.model.TripPartecipationTest.class,
        ChildCareTech.model.TripTest.class,
        ChildCareTech.model.WorkDayTest.class
})

public class EntityTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}