package ChildCareTech;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChildCareTech.entities.AdultTest.class,
        ChildCareTech.entities.CanteenTest.class,
        ChildCareTech.entities.DishTest.class,
        ChildCareTech.entities.DrinkTest.class,
        ChildCareTech.entities.EventTest.class,
        ChildCareTech.entities.FoodTest.class,
        ChildCareTech.entities.KidTest.class,
        ChildCareTech.entities.MealTest.class,
        ChildCareTech.entities.MenuTest.class,
        ChildCareTech.entities.PediatristTest.class,
        ChildCareTech.entities.PersonTest.class,
        ChildCareTech.entities.StaffTest.class,
        ChildCareTech.entities.SupplierTest.class,
        ChildCareTech.entities.SupplyTest.class,
        ChildCareTech.entities.TripTest.class,
        ChildCareTech.entities.WorkDayTest.class
})

public class EntityTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}