package ChildCareTechTest.controller.SupplierController;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.SupplierController;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.entities.Supplier;
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
 * Black-box test of {@link SupplierController#doRemoveSupplier(SupplierDTO)} .
 */
public class DoRemoveSupplierTest extends AbstractControllerActionTest {
    private SupplierController supplierController = new SupplierController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        SupplierDAO supplierDAO = new SupplierDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Supplier supplier = new Supplier(p1);
        SupplierDTO supplierDTO = DTOFactoryFacade.getDTO(supplier);

        supplierController.doRemoveSupplier(supplierDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        supplierDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            supplierDAO.create(supplier);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        supplierDAO.setSession(session1);
        assertEquals(supplierDAO.read(supplier), supplier);
        session1.close();

        supplierDTO = DTOFactoryFacade.getDTO(supplier);
        supplierController.doRemoveSupplier(supplierDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        supplierDAO.setSession(session2);
        assertNull(supplierDAO.read(supplier));
        session2.close();

    }
}
