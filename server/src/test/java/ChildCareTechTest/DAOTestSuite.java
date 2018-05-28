package ChildCareTechTest;

import ChildCareTechTest.model.DAO.AvailableBusTest;
import ChildCareTechTest.model.DAO.AvailableKidTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite grouping all tests in {@link ChildCareTechTest.model.DAO DAO} package.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AvailableBusTest.class,
        AvailableKidTest.class
})

public class DAOTestSuite {}
