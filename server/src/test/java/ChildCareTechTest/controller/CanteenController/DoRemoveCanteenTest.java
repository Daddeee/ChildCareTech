package ChildCareTechTest.controller.CanteenController;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.CanteenController;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link CanteenController#doRemoveCanteen(CanteenDTO)} .
 */
public class DoRemoveCanteenTest extends AbstractControllerActionTest {
    private CanteenController canteenController = new CanteenController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        CanteenDAO canteenDAO = new CanteenDAO();

        Canteen canteen = new Canteen("Nuova mensa");
        CanteenDTO canteenDTO = DTOFactoryFacade.getDTO(canteen);

        canteenController.doRemoveCanteen(canteenDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        canteenDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            canteenDAO.create(canteen);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session1);
        assertEquals(canteenDAO.read(canteen), canteen);
        session1.close();

        canteenDTO = DTOFactoryFacade.getDTO(canteen);
        canteenController.doRemoveCanteen(canteenDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session2);
        assertNull(canteenDAO.read(canteen));
        session2.close();

    }
}