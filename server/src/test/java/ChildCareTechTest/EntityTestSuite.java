package ChildCareTechTest;

import ChildCareTechTest.model.entities.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite grouping all tests in {@link ChildCareTechTest.model.entities entities} package.
 */

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

public class EntityTestSuite {}