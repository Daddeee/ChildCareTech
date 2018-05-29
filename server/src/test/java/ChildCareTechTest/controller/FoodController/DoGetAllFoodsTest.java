package ChildCareTechTest.controller.FoodController;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.controller.FoodController;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link FoodController#doGetAllFoods()}.
 */
public class DoGetAllFoodsTest extends AbstractControllerActionTest {
    private FoodController foodController = new FoodController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        FoodDAO foodDAO = new FoodDAO();

        Food b1 = new Food("farina", false);
        Food b2 = new Food("uova", false);
        Food b3 = new Food("latte", true);

        List<FoodDTO> expectedEmpty = foodController.doGetAllFoods();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            foodDAO.create(b1);
            foodDAO.create(b2);
            foodDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<FoodDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<FoodDTO> result = foodController.doGetAllFoods();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
