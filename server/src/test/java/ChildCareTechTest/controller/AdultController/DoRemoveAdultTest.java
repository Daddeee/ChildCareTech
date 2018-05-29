package ChildCareTechTest.controller.AdultController;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.AdultController;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.entities.Adult;
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
 * Black-box test of {@link AdultController#doRemoveAdult(AdultDTO)} .
 */
public class DoRemoveAdultTest extends AbstractControllerActionTest {
    private AdultController adultController = new AdultController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        AdultDAO adultDAO = new AdultDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Adult adult = new Adult(p1);
        AdultDTO adultDTO = DTOFactoryFacade.getDTO(adult);

        adultController.doRemoveAdult(adultDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        adultDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            adultDAO.create(adult);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        adultDAO.setSession(session1);
        assertEquals(adultDAO.read(adult), adult);
        session1.close();

        adultDTO = DTOFactoryFacade.getDTO(adult);
        adultController.doRemoveAdult(adultDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        adultDAO.setSession(session2);
        assertNull(adultDAO.read(adult));
        session2.close();

    }
}
