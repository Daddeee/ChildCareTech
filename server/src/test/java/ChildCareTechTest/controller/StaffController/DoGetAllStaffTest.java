package ChildCareTechTest.controller.StaffController;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.StaffController;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.StaffDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link StaffController#doGetAllStaff())} .
 */
public class DoGetAllStaffTest extends AbstractControllerActionTest {
    private StaffController staffController = new StaffController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        StaffDAO staffDAO = new StaffDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Person p3 = new Person("0000000000000003", "Francesco", "Alberti", LocalDate.parse("1996-05-07"), Sex.FEMALE, "via Caccialupo 12", "1234567890");

        Staff b1 = new Staff(p1);
        Staff b2 = new Staff(p2);
        Staff b3 = new Staff(p3);

        List<StaffDTO> expectedEmpty = staffController.doGetAllStaff();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            staffDAO.create(b1);
            staffDAO.create(b2);
            staffDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<StaffDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<StaffDTO> result = staffController.doGetAllStaff();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
