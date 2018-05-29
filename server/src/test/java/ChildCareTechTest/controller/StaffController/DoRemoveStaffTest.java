package ChildCareTechTest.controller.StaffController;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.StaffController;
import ChildCareTech.model.DAO.StaffDAO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link StaffController#doRemoveStaff(StaffDTO)} .
 */
public class DoRemoveStaffTest extends AbstractControllerActionTest {
    private StaffController staffController = new StaffController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        StaffDAO staffDAO = new StaffDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Staff staff = new Staff(p1);
        StaffDTO staffDTO = DTOFactoryFacade.getDTO(staff);

        staffController.doRemoveStaff(staffDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            staffDAO.create(staff);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        staffDAO.setSession(session1);
        assertEquals(staffDAO.read(staff), staff);
        session1.close();

        staffDTO = DTOFactoryFacade.getDTO(staff);
        staffController.doRemoveStaff(staffDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        staffDAO.setSession(session2);
        assertNull(staffDAO.read(staff));
        session2.close();

    }
}
