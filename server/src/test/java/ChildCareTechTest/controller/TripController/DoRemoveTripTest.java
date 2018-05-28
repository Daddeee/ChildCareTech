package ChildCareTechTest.controller.TripController;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.TripController;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link TripController#doRemoveTrip(TripDTO)} .
 */
public class DoRemoveTripTest extends AbstractControllerActionTest {
    private TripController tripController = new TripController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        TripDAO tripDAO = new TripDAO();

        Trip trip = new Trip("Cremona", "note", LocalDate.now(), LocalDate.now(), EventStatus.OPEN);
        TripDTO tripDTO = DTOFactoryFacade.getDTO(trip);

        tripController.doRemoveTrip(tripDTO);

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
        tripDAO.setSession(session1);
        assertEquals(tripDAO.read(trip), trip);
        session1.close();

        tripDTO = DTOFactoryFacade.getDTO(trip);
        tripController.doRemoveTrip(tripDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session2);
        assertNull(tripDAO.read(trip));
        session2.close();

    }
}
