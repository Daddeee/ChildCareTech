package ChildCareTechTest.controller.SupplierController;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.SupplierController;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supplier;
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
 * Black-box test of {@link SupplierController#doGetAllSuppliers())} .
 */
public class DoGetAllSuppliersTest extends AbstractControllerActionTest {
    private SupplierController supplierController = new SupplierController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        SupplierDAO supplierDAO = new SupplierDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Person p3 = new Person("0000000000000003", "Francesco", "Alberti", LocalDate.parse("1996-05-07"), Sex.FEMALE, "via Caccialupo 12", "1234567890");

        Supplier b1 = new Supplier(p1);
        Supplier b2 = new Supplier(p2);
        Supplier b3 = new Supplier(p3);

        List<SupplierDTO> expectedEmpty = supplierController.doGetAllSuppliers();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        supplierDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            supplierDAO.create(b1);
            supplierDAO.create(b2);
            supplierDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<SupplierDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<SupplierDTO> result = supplierController.doGetAllSuppliers();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
