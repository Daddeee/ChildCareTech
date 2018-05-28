package ChildCareTechTest.controller.PediatristController;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.PediatristController;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link PediatristController#doSavePediatrist(PediatristDTO)}.
 */
public class DoSavePediatristTest extends AbstractControllerActionTest {
    private PediatristController pediatristController = new PediatristController();

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
        PediatristDAO pediatristDAO = new PediatristDAO();

        PersonDTO p1 = new PersonDTO(0, "0000000000000001", "Pediatristo", "Da creare", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO p2 = new PersonDTO(0, "0000000000000001", "Pediatristo", "Stesso codice fiscale", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidFiscalCode = new PersonDTO(0, "000000000000000", "Pediatristo", "Codice Fiscale da 15 cifre", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidPhoneNumber = new PersonDTO(0, "0000000000000001", "Pediatristo", "Numero di telefono assente", LocalDate.now(), Sex.MALE, "", "", null);

        PediatristDTO toBeCreated = new PediatristDTO(0, p1, null, null);
        PediatristDTO toFailCreation = new PediatristDTO(0, p2, null, null);
        PediatristDTO toFailValidationOfFiscalCode = new PediatristDTO(0, invalidFiscalCode, null, null);
        PediatristDTO toFailValidationOfPhoneNumber = new PediatristDTO(0, invalidPhoneNumber, null, null);

        try {
            pediatristController.doSavePediatrist(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            pediatristController.doSavePediatrist(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            pediatristController.doSavePediatrist(toFailValidationOfFiscalCode);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            pediatristController.doSavePediatrist(toFailValidationOfPhoneNumber);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        pediatristDAO.setSession(session);
        personDAO.setSession(session);

        Person readPerson = personDAO.read("fiscalCode", "0000000000000001").get(0);
        Pediatrist read = pediatristDAO.read("person_id", readPerson.getId()).get(0);
        Pediatrist original = EntityFactoryFacade.getEntity(toBeCreated);
        assertEquals(read, original);

        session.close();
    }
}
