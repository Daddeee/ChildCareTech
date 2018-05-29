package ChildCareTechTest.controller.DishController;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.controller.DishController;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link DishController#doGetAllDishes()}.
 */
public class DoGetAllDishesTest extends AbstractControllerActionTest {
    private DishController dishController = new DishController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        DishDAO dishDAO = new DishDAO();

        Dish d1 = new Dish("pasta rossa");
        Dish d2 = new Dish("pizza");
        Dish d3 = new Dish("torta salata");

        List<DishDTO> expectedEmpty = dishController.doGetAllDishes();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            dishDAO.create(d1);
            dishDAO.create(d2);
            dishDAO.create(d3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<DishDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(d1));
        expectedResult.add(DTOFactoryFacade.getDTO(d2));
        expectedResult.add(DTOFactoryFacade.getDTO(d3));

        List<DishDTO> result = dishController.doGetAllDishes();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
