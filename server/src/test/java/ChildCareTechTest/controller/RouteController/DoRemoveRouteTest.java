package ChildCareTechTest.controller.RouteController;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.RouteController;
import ChildCareTech.model.DAO.RouteDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link RouteController#doRemoveRoute(RouteDTO)} .
 */
public class DoRemoveRouteTest extends AbstractControllerActionTest {
    private RouteController routeController = new RouteController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        RouteDAO routeDAO = new RouteDAO();
        TripDAO tripDAO = new TripDAO();

        Trip trip = new Trip(
                "Cremona",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN
        );
        trip.setRoutes(new HashSet<>());
        Route correct1 = new Route(trip, 1, "Piacenza", "Cremona", EventStatus.WAIT, null,null);
        Route correct2 = new Route(trip, 2, "Cremona", "Piacenza", EventStatus.WAIT, null, null);
        trip.getRoutes().add(correct1);
        trip.getRoutes().add(correct2);

        RouteDTO routeDTO = DTOFactoryFacade.getDTO(correct1);

        routeController.doRemoveRoute(routeDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            tripDAO.create(trip);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        routeDAO.setSession(session1);
        assertEquals(routeDAO.read(correct1), correct1);
        session1.close();

        routeDTO = DTOFactoryFacade.getDTO(correct1);
        routeController.doRemoveRoute(routeDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        routeDAO.setSession(session2);
        assertNull(routeDAO.read(correct1));
        session2.close();

    }
}