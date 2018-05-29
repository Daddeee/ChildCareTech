package ChildCareTechTest.controller.FoodController;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.FoodController;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.entities.Food;
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
 * Black-box test of {@link FoodController#doRemoveFood(FoodDTO)} .
 */
public class DoRemoveFoodTest extends AbstractControllerActionTest {
    private FoodController foodController = new FoodController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        FoodDAO foodDAO = new FoodDAO();

        Food food = new Food("latte", true);
        FoodDTO foodDTO = DTOFactoryFacade.getDTO(food);

        foodController.doRemoveFood(foodDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            foodDAO.create(food);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        foodDAO.setSession(session1);
        assertEquals(foodDAO.read(food), food);
        session1.close();

        foodDTO = DTOFactoryFacade.getDTO(food);
        foodController.doRemoveFood(foodDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        foodDAO.setSession(session2);
        assertNull(foodDAO.read(food));
        session2.close();

    }
}