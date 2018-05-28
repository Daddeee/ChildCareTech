package ChildCareTechTest.controller.TripController;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.TripController;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Trip;
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
 * Black-box test of {@link TripController#doGetAllTrips()} .
 */
public class DoGetAllTripsTest extends AbstractControllerActionTest {
    private TripController tripController = new TripController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        TripDAO tripDAO = new TripDAO();

        Trip b1 = new Trip("Cremona", "nota", LocalDate.now(), LocalDate.now(), EventStatus.OPEN);
        Trip b2 = new Trip("Cremona", "nota", LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), EventStatus.OPEN);
        Trip b3 = new Trip("Piacenza", "nota", LocalDate.now(), LocalDate.now(), EventStatus.OPEN);

        List<TripDTO> expectedEmpty = tripController.doGetAllTrips();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            tripDAO.create(b1);
            tripDAO.create(b2);
            tripDAO.create(b3);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        List<TripDTO> expectedResult = new ArrayList<>();
        expectedResult.add(DTOFactoryFacade.getDTO(b1));
        expectedResult.add(DTOFactoryFacade.getDTO(b2));
        expectedResult.add(DTOFactoryFacade.getDTO(b3));

        List<TripDTO> result = tripController.doGetAllTrips();

        assert(result.size() == expectedResult.size() && result.containsAll(expectedResult));
    }
}
