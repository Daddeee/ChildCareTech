package ChildCareTechTest.controller.PediatristController;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.PediatristController;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Pediatrist;
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
 * Black-box test of {@link PediatristController#doGetAllPediatrists())} .
 */
public class DoGetAllPediatristsTest extends AbstractControllerActionTest {
    private PediatristController pediatristController = new PediatristController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Person p3 = new Person("0000000000000003", "Francesco", "Alberti", LocalDate.parse("1996-05-07"), Sex.FEMALE, "via Caccialupo 12", "1234567890");

        Pediatrist b1 = new Pediatrist(p1);
        Pediatrist b2 = new Pediatrist(p2);
        Pediatrist b3 = new Pediatrist(p3);

        List<PediatristDTO> expectedEmpty = pediatristController.doGetAllPediatrists();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            pediatristDAO.create(b1);
            pediatristDAO.create(b2);
            pediatristDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<PediatristDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<PediatristDTO> result = pediatristController.doGetAllPediatrists();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
