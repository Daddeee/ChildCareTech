package ChildCareTechTest.controller.BusController;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.BusController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link BusController#doGetAllBuses()} .
 */
public class DoGetAllBusesTest extends AbstractControllerActionTest {
    private BusController busController = new BusController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        BusDAO busDAO = new BusDAO();

        Bus b1 = new Bus("AA111AA", 10);
        Bus b2 = new Bus("BB222BB", 5);
        Bus b3 = new Bus("CC333CC", 20);

        List<BusDTO> expectedEmpty = busController.doGetAllBuses();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            busDAO.create(b1);
            busDAO.create(b2);
            busDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<BusDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<BusDTO> result = busController.doGetAllBuses();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
