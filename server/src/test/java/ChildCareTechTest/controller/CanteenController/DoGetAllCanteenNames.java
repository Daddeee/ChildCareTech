package ChildCareTechTest.controller.CanteenController;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.controller.CanteenController;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link CanteenController#doGetAllCanteenNames()}.
 */
public class DoGetAllCanteenNames extends AbstractControllerActionTest {
    private CanteenController canteenController = new CanteenController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        CanteenDAO canteenDAO = new CanteenDAO();

        List<String> expectedEmpty = canteenController.doGetAllCanteenNames();
        assert(expectedEmpty.isEmpty());

        String name1 = "Primo nome";
        String name2 = "Secondo nome";

        Canteen canteen1 = new Canteen(name1);
        Canteen canteen2 = new Canteen(name2);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        canteenDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            canteenDAO.create(canteen1);
            canteenDAO.create(canteen2);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(name1);
        expectedResult.add(name2);

        List<String> result = canteenController.doGetAllCanteenNames();
        assert (result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
