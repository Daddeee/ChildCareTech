package ChildCareTechTest.controller.DishController;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.DishController;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Dish;
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
 * Black-box test of {@link DishController#doDeleteDish(DishDTO)} .
 */
public class DoDeleteDishTest extends AbstractControllerActionTest {
    private DishController dishController = new DishController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        DishDAO dishDAO = new DishDAO();

        Dish dish = new Dish("pizza");
        DishDTO dishDTO = DTOFactoryFacade.getDTO(dish);

        dishController.doDeleteDish(dishDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            dishDAO.create(dish);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session1);
        assertEquals(dishDAO.read(dish), dish);
        session1.close();

        dishDTO = DTOFactoryFacade.getDTO(dish);
        dishController.doDeleteDish(dishDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session2);
        assertNull(dishDAO.read(dish));
        session2.close();

    }
}