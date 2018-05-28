package ChildCareTechTest.controller.AdultController;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.AdultController;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link AdultController#doSaveAdult(AdultDTO)}.
 */
public class DoSaveAdultTest extends AbstractControllerActionTest {
    private AdultController adultController = new AdultController();

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
        AdultDAO adultDAO = new AdultDAO();

        PersonDTO p1 = new PersonDTO(0, "0000000000000001", "Adulto", "Da creare", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO p2 = new PersonDTO(0, "0000000000000001", "Adulto", "Stesso codice fiscale", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidFiscalCode = new PersonDTO(0, "000000000000000", "Adulto", "Codice Fiscale da 15 cifre", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidPhoneNumber = new PersonDTO(0, "0000000000000001", "Adulto", "Numero di telefono assente", LocalDate.now(), Sex.MALE, "", "", null);

        AdultDTO toBeCreated = new AdultDTO(0, p1, null);
        AdultDTO toFailCreation = new AdultDTO(0, p2, null);
        AdultDTO toFailValidationOfFiscalCode = new AdultDTO(0, invalidFiscalCode, null);
        AdultDTO toFailValidationOfPhoneNumber = new AdultDTO(0, invalidPhoneNumber, null);

        try {
            adultController.doSaveAdult(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            adultController.doSaveAdult(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            adultController.doSaveAdult(toFailValidationOfFiscalCode);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            adultController.doSaveAdult(toFailValidationOfPhoneNumber);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        adultDAO.setSession(session);
        personDAO.setSession(session);

        Person readPerson = personDAO.read("fiscalCode", "0000000000000001").get(0);
        Adult read = adultDAO.read("person_id", readPerson.getId()).get(0);
        Adult original = EntityFactoryFacade.getEntity(toBeCreated);
        assertEquals(read, original);

        session.close();
    }
}
