package ChildCareTechTest.controller.StaffController;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.StaffController;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.StaffDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link StaffController#doSaveStaff(StaffDTO)}.
 */
public class DoSaveStaffTest extends AbstractControllerActionTest {
    private StaffController staffController = new StaffController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating an already present entity</li>
     *     <li>creating entities that do not meet the validation requirements</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        StaffDAO staffDAO = new StaffDAO();

        PersonDTO p1 = new PersonDTO(0, "0000000000000001", "Staffo", "Da creare", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO p2 = new PersonDTO(0, "0000000000000001", "Staffo", "Stesso codice fiscale", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidFiscalCode = new PersonDTO(0, "000000000000000", "Staffo", "Codice Fiscale da 15 cifre", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidPhoneNumber = new PersonDTO(0, "0000000000000001", "Staffo", "Numero di telefono assente", LocalDate.now(), Sex.MALE, "", "", null);

        StaffDTO toBeCreated = new StaffDTO(0, p1, null);
        StaffDTO toFailCreation = new StaffDTO(0, p2, null);
        StaffDTO toFailValidationOfFiscalCode = new StaffDTO(0, invalidFiscalCode, null);
        StaffDTO toFailValidationOfPhoneNumber = new StaffDTO(0, invalidPhoneNumber, null);

        try {
            staffController.doSaveStaff(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            staffController.doSaveStaff(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            staffController.doSaveStaff(toFailValidationOfFiscalCode);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            staffController.doSaveStaff(toFailValidationOfPhoneNumber);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        staffDAO.setSession(session);
        personDAO.setSession(session);

        Person readPerson = personDAO.read("fiscalCode", "0000000000000001").get(0);
        Staff read = staffDAO.read("person_id", readPerson.getId()).get(0);
        Staff original = EntityFactoryFacade.getEntity(toBeCreated);
        assertEquals(read, original);

        session.close();
    }
}
