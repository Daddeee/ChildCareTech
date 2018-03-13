package ChildCareTech.model;

import java.time.LocalDate;

public class WorkDayTest extends AbstractEntityTest<WorkDay> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = WorkDay.class;
    }

    @Override
    public void testCRUD() {
        WorkDay d = new WorkDay(LocalDate.now());
        WorkDay du = new WorkDay(LocalDate.now().plusDays(1));

        testCRUDImpl(d, du);
    }
}
