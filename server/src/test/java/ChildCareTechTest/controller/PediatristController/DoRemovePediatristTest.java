package ChildCareTechTest.controller.PediatristController;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.PediatristController;
import ChildCareTech.model.DAO.PediatristDAO;
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
 * Black-box test of {@link PediatristController#doRemovePediatrist(PediatristDTO)} .
 */
public class DoRemovePediatristTest extends AbstractControllerActionTest {
    private PediatristController pediatristController = new PediatristController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Pediatrist pediatrist = new Pediatrist(p1);
        PediatristDTO pediatristDTO = DTOFactoryFacade.getDTO(pediatrist);

        pediatristController.doRemovePediatrist(pediatristDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            pediatristDAO.create(pediatrist);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        pediatristDAO.setSession(session1);
        assertEquals(pediatristDAO.read(pediatrist), pediatrist);
        session1.close();

        pediatristDTO = DTOFactoryFacade.getDTO(pediatrist);
        pediatristController.doRemovePediatrist(pediatristDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        pediatristDAO.setSession(session2);
        assertNull(pediatristDAO.read(pediatrist));
        session2.close();

    }
}

