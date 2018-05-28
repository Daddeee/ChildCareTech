package ChildCareTechTest;

import ChildCareTechTest.controller.GetCanteenByNameTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite grouping all tests in {@link ChildCareTechTest.controller controller} package.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetCanteenByNameTest.class
})

public class ControllerTestSuite {
}
