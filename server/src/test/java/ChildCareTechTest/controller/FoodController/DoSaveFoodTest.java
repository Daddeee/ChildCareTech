package ChildCareTechTest.controller.FoodController;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.FoodController;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link FoodController#doSaveFood(FoodDTO)}.
 */
public class DoSaveFoodTest extends AbstractControllerActionTest {
    private FoodController controller = new FoodController();
    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating a food entity with duplicated name</li>
     * </ul>
     */
    @Override
    public void testAction() {
        FoodDAO foodDAO = new FoodDAO();

        FoodDTO toBeCreated = new FoodDTO(0, "patata", false, null, null);
        FoodDTO toFailCreation = new FoodDTO(0, "patata", false, null, null);

        try {
            controller.doSaveFood(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            controller.doSaveFood(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}


        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Food original = EntityFactoryFacade.getEntity(toBeCreated);
        foodDAO.setSession(session);
        Food read = foodDAO.read("name", original.getName()).get(0);
        assertEquals(read, original);

        session.close();
    }
}
