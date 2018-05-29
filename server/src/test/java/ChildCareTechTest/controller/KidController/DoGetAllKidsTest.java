package ChildCareTechTest.controller.KidController;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.KidController;
import ChildCareTech.model.DAO.KidDAO;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.model.entities.Person;
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
 * Black-box test of {@link  KidController#doGetAllKids()}.
 */
public class DoGetAllKidsTest extends AbstractControllerActionTest {
    private KidController kidController = new KidController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PediatristDAO pediatristDAO = new PediatristDAO();
        KidDAO kidDAO = new KidDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Pediatrist pediatrist = new Pediatrist(p1);

        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Person p3 = new Person("0000000000000003", "Francesco", "Alberti", LocalDate.parse("1996-05-07"), Sex.FEMALE, "via Caccialupo 12", "1234567890");
        Person p4 = new Person("0000000000000004", "Elia", "Bettola", LocalDate.parse("1996-10-05"), Sex.MALE, "via Martiri del lavoro 41", "1234567890");

        Kid k1 = new Kid(p2, pediatrist, null, pediatrist);
        Kid k2 = new Kid(p3, pediatrist, null, pediatrist);
        Kid k3 = new Kid(p4, pediatrist, null, pediatrist);

        List<KidDTO> expectedEmpty = kidController.doGetAllKids();
        assert (expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        pediatristDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            pediatristDAO.create(pediatrist);
            kidDAO.create(k1);
            kidDAO.create(k2);
            kidDAO.create(k3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        }

        List<KidDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(k1));
        expectedResult.add(DTOFactoryFacade.getDTO(k2));
        expectedResult.add(DTOFactoryFacade.getDTO(k3));

        List<KidDTO> result = kidController.doGetAllKids();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));

    }
}
