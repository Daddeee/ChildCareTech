package ChildCareTechTest.controller.DishController;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import ChildCareTech.controller.DishController;
import org.hibernate.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link DishController#doCreateDish(DishDTO)}.
 */
public class DoCreateDishTest extends AbstractControllerActionTest {
    private DishController controller = new DishController();

    /**
     * Testing normal behavior of this method.
     */
    @Override
    public void testAction() {
        DishDAO dishDAO = new DishDAO();

        DishDTO toBeCreated = new DishDTO(0, "Nuovo piatto", null, null);

        try {
            controller.doCreateDish(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Dish original = EntityFactoryFacade.getEntity(toBeCreated);
        dishDAO.setSession(session);
        Dish read = dishDAO.read("name", original.getName()).get(0);
        assertEquals(read, original);

        session.close();
    }
}
