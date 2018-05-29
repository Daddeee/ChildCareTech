package ChildCareTechTest.controller.DishController;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.DishController;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link DishController#doUpdateDish(DishDTO)}.
 */
public class DoUpdateDishTest extends AbstractControllerActionTest {
    private DishController dishController = new DishController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        DishDAO dishDAO = new DishDAO();
        Dish saved = new Dish("patata");
        Dish saved2 = new Dish("duplicato");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            dishDAO.create(saved);
            dishDAO.create(saved2);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        DishDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setName("ok");

        try {
            dishController.doUpdateDish(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        DishDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setName("duplicato");
        try{
            dishController.doUpdateDish(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session1);
        Dish read = dishDAO.read(updated.getId());
        DishDTO readDTO = DTOFactoryFacade.getDTO(read);
        assert (readDTO.equals(updated));

        session1.close();
    }
}