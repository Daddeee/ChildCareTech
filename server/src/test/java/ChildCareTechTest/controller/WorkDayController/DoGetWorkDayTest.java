package ChildCareTechTest.controller.WorkDayController;

import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.controller.WorkDayController;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link  WorkDayController#doGetWorkDay(LocalDate)}.
 */
public class DoGetWorkDayTest extends AbstractControllerActionTest {
    private WorkDayController workDayController = new WorkDayController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling with an unsaved date.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        WorkDayDAO workDayDAO = new WorkDayDAO();
        LocalDate date = LocalDate.now();

        WorkDay workDay = new WorkDay(date, LocalTime.MIN, LocalTime.NOON, false);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            workDayDAO.create(workDay);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        WorkDayDTO workDayDTO = workDayController.doGetWorkDay(date);
        WorkDay result = EntityFactoryFacade.getEntity(workDayDTO);

        assertEquals(result, workDay);

    }
}
