package ChildCareTechTest.controller.KidController;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.KidController;
import ChildCareTech.model.DAO.KidDAO;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
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
 * Black-box test of {@link KidController#doRemoveKid(KidDTO)} .
 */
public class DoRemoveKidTest extends AbstractControllerActionTest {
    private KidController kidController = new KidController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        KidDAO kidDAO = new KidDAO();
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Pediatrist pediatrist = new Pediatrist(p2);

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Kid kid = new Kid(p1, pediatrist, null, pediatrist);

        KidDTO kidDTO = DTOFactoryFacade.getDTO(kid);
        kidController.doRemoveKid(kidDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            pediatristDAO.create(pediatrist);
            kidDAO.create(kid);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        kidDAO.setSession(session1);
        assertEquals(kidDAO.read(kid), kid);
        session1.close();

        kidDTO = DTOFactoryFacade.getDTO(kid);
        kidController.doRemoveKid(kidDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        kidDAO.setSession(session2);
        assertNull(kidDAO.read(kid));
        session2.close();

    }
}

