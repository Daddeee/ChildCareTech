package ChildCareTechTest.controller.TripController;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.TripController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.RouteDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link TripController#doUpdateTrip(TripDTO)}.
 */
public class DoUpdateTripTest extends AbstractControllerActionTest {
    private TripController tripController = new TripController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        TripDAO tripDAO = new TripDAO();
        RouteDAO routeDAO = new RouteDAO();
        BusDAO busDAO = new BusDAO();

        Trip saved = new Trip(
                "Cremona",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN
        );
        Route correct1 = new Route(saved, 1, "Piacenza", "Cremona", EventStatus.WAIT, null,null);
        Route correct2 = new Route(saved, 2, "Cremona", "Piacenza", EventStatus.WAIT, null, null);
        saved.setRoutes(new HashSet<>());
        saved.getRoutes().add(correct1);
        saved.getRoutes().add(correct2);

        Bus bus = new Bus("AA111AA", 10);
        saved.setBuses(new HashSet<>());
        saved.getBuses().add(bus);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripDAO.setSession(session);
        routeDAO.setSession(session);
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            busDAO.create(bus);
            tripDAO.create(saved);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        TripDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setMeta("Lodi");

        try {
            tripController.doUpdateTrip(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        TripDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setMeta("");
        try{
            tripController.doUpdateTrip(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}
        failUpdated.setMeta("Lodi");

        failUpdated.setArrDate(LocalDate.MAX);
        try{
            tripController.doUpdateTrip(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session1);
        Trip read = tripDAO.read(updated.getId());
        TripDTO readDTO = DTOFactoryFacade.getDTO(read);
        assert (readDTO.equals(updated));

        session1.close();
    }
}