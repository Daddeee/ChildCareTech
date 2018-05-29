package ChildCareTechTest.controller.SupplierController;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.SupplierController;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link SupplierController#doUpdateSupplier(SupplierDTO)}.
 */
public class DoUpdateSupplierTest extends AbstractControllerActionTest {
    private SupplierController supplierController = new SupplierController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        SupplierDAO supplierDAO = new SupplierDAO();

        Person p1 = new Person("0000000000000001", "Supplier", "Da creare", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("0000000000000002", "Supplier", "Aggiornato", LocalDate.now(), Sex.MALE, "", "1");
        Supplier saved = new Supplier(p1);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        supplierDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(p1);
            personDAO.create(p2);
            supplierDAO.create(saved);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PersonDTO newPerson = DTOFactoryFacade.getDTO(p2);
        SupplierDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setPerson(newPerson);

        try {
            supplierController.doUpdateSupplier(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        SupplierDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setPerson(DTOFactoryFacade.getDTO(p2));

        failUpdated.getPerson().setPhoneNumber("");
        try{
            supplierController.doUpdateSupplier(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        failUpdated.getPerson().setPhoneNumber("1");
        failUpdated.getPerson().setFiscalCode("0000");
        try{
            supplierController.doUpdateSupplier(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        supplierDAO.setSession(session1);
        personDAO.setSession(session1);

        Supplier read = supplierDAO.read(updated.getId());
        SupplierDTO readDTO = DTOFactoryFacade.getDTO(read);
        assertEquals(readDTO, updated);

        session1.close();
    }
}