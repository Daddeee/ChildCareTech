package ChildCareTechTest.controller.FoodController;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.FoodController;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link FoodController#doUpdateFood(FoodDTO)}.
 */
public class DoUpdateFoodTest extends AbstractControllerActionTest {
    private FoodController foodController = new FoodController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        FoodDAO foodDAO = new FoodDAO();
        Food saved = new Food("patata", false);
        Food saved2 = new Food("duplicato", false);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            foodDAO.create(saved);
            foodDAO.create(saved2);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        FoodDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setName("ok");

        try {
            foodController.doUpdateFood(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        FoodDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setName("duplicato");
        try{
            foodController.doUpdateFood(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        foodDAO.setSession(session1);
        Food read = foodDAO.read(updated.getId());
        FoodDTO readDTO = DTOFactoryFacade.getDTO(read);
        assert (readDTO.equals(updated));

        session1.close();
    }
}