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
 * Black-box test of {@link CanteenController#doGetAllCanteenes()}.
 */
public class DoGetAllCanteenesTest extends AbstractControllerActionTest {
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

        List<CanteenDTO> expectedEmpty = canteenController.doGetAllCanteenes();
        assert(expectedEmpty.isEmpty());

        Canteen canteen1 = new Canteen("Prima mensa");
        Canteen canteen2 = new Canteen("Seconda mensa");

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

        List<CanteenDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(canteen1));
        expectedResult.add(DTOFactoryFacade.getDTO(canteen2));

        List<CanteenDTO> result = canteenController.doGetAllCanteenes();
        assert (result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
