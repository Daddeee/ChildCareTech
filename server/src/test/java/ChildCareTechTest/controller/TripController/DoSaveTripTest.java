package ChildCareTechTest.controller.TripController;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.TripController;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link TripController#doSaveTrip(TripDTO)}.
 */
public class DoSaveTripTest extends AbstractControllerActionTest {
    private TripController tripController = new TripController();

    /**
     * Testing normal behavior and limit cases for this method. See the {@link ChildCareTech.common.UserSessionFacade#saveTrip(TripDTO)}  original method}
     * for complete specifications.
     */
    @Override
    public void testAction() {
        TripDAO tripDAO = new TripDAO();

        TripDTO toBeCreated = new TripDTO(
                0,
                "Cremona",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN,
                new HashSet<>(),
                null,
                null
        );
        RouteDTO correct1 = new RouteDTO(0, toBeCreated, 1, "Piacenza", "Cremona", EventStatus.WAIT, null,null);
        RouteDTO correct2 = new RouteDTO(0, toBeCreated, 2, "Cremona", "Piacenza", EventStatus.WAIT, null, null);
        toBeCreated.getRoutes().add(correct1);
        toBeCreated.getRoutes().add(correct2);

        TripDTO toFailCreation = new TripDTO(
                0,
                "Cremona",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN,
                new HashSet<>(),
                null,
                null
        );
        toFailCreation.getRoutes().add(correct1);
        toFailCreation.getRoutes().add(correct2);

        TripDTO toFailValidationOfDates = new TripDTO(
                0,
                "Milano",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().minusDays(1),
                EventStatus.OPEN,
                new HashSet<>(),
                null,
                null
        );
        toFailValidationOfDates.getRoutes().add(correct1);
        toFailValidationOfDates.getRoutes().add(correct2);

        TripDTO toFailValidationOfRoutes = new TripDTO(
                0,
                "Lodi",
                "gita creata con successo",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                EventStatus.OPEN,
                new HashSet<>(),
                null,
                null
        );
        RouteDTO invalid1 = new RouteDTO(0, toBeCreated, 1, "Piacenza", "Lodi", EventStatus.WAIT, null,null);
        RouteDTO invalid2 = new RouteDTO(0, toBeCreated, 2, "Cremona", "Piacenza", EventStatus.WAIT, null, null);
        toFailValidationOfRoutes.getRoutes().add(invalid1);
        toFailValidationOfRoutes.getRoutes().add(invalid2);


        try {
            tripController.doSaveTrip(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            tripController.doSaveTrip(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            tripController.doSaveTrip(toFailValidationOfDates);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            tripController.doSaveTrip(toFailValidationOfRoutes);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripDAO.setSession(session);

        Trip original = EntityFactoryFacade.getEntity(toBeCreated);
        Trip read = tripDAO.read("meta", original.getMeta()).get(0);
        assertEquals(read, original);

        session.close();
    }
}
