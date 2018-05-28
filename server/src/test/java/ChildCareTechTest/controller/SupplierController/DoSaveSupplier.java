package ChildCareTechTest.controller.SupplierController;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.SupplierController;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link SupplierController#doSaveSupplier(SupplierDTO)}.
 */
public class DoSaveSupplier extends AbstractControllerActionTest {
    private SupplierController supplierController = new SupplierController();

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
        SupplierDAO supplierDAO = new SupplierDAO();

        PersonDTO p1 = new PersonDTO(0, "0000000000000001", "Suppliero", "Da creare", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO p2 = new PersonDTO(0, "0000000000000001", "Suppliero", "Stesso codice fiscale", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidFiscalCode = new PersonDTO(0, "000000000000000", "Suppliero", "Codice Fiscale da 15 cifre", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO invalidPhoneNumber = new PersonDTO(0, "0000000000000001", "Suppliero", "Numero di telefono assente", LocalDate.now(), Sex.MALE, "", "", null);

        SupplierDTO toBeCreated = new SupplierDTO(0, p1, null, null);
        SupplierDTO toFailCreation = new SupplierDTO(0, p2, null, null);
        SupplierDTO toFailValidationOfFiscalCode = new SupplierDTO(0, invalidFiscalCode, null, null);
        SupplierDTO toFailValidationOfPhoneNumber = new SupplierDTO(0, invalidPhoneNumber, null, null);

        try {
            supplierController.doSaveSupplier(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            supplierController.doSaveSupplier(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            supplierController.doSaveSupplier(toFailValidationOfFiscalCode);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            supplierController.doSaveSupplier(toFailValidationOfPhoneNumber);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        supplierDAO.setSession(session);
        personDAO.setSession(session);

        Person readPerson = personDAO.read("fiscalCode", "0000000000000001").get(0);
        Supplier read = supplierDAO.read("person_id", readPerson.getId()).get(0);
        Supplier original = EntityFactoryFacade.getEntity(toBeCreated);
        assertEquals(read, original);

        session.close();
    }
}
