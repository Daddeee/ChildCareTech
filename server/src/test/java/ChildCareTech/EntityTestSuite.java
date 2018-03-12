package ChildCareTech;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChildCareTech.entity.AdultTest.class,
        ChildCareTech.entity.CanteenTest.class,
        ChildCareTech.entity.DishTest.class,
        ChildCareTech.entity.DrinkTest.class,
        ChildCareTech.entity.FoodTest.class,
        ChildCareTech.entity.KidTest.class,
        ChildCareTech.entity.MealTest.class,
        ChildCareTech.entity.MenuTest.class,
        ChildCareTech.entity.PediatristTest.class,
        ChildCareTech.entity.PersonTest.class,
        ChildCareTech.entity.StaffTest.class,
        ChildCareTech.entity.SupplierTest.class,
        ChildCareTech.entity.SupplyTest.class
})

public class EntityTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}