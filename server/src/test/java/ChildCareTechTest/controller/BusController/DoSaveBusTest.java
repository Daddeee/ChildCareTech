package ChildCareTechTest.controller.BusController;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.controller.BusController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link BusController#doSaveBus(BusDTO)}.
 */
public class DoSaveBusTest extends AbstractControllerActionTest {
    private BusController controller= new BusController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating an already present entity</li>
     *     <li>creating entities that do not meet the validation requirements</li>
     * </ul>
     */
    @Override
    public void testAction() {
        BusDAO busDAO = new BusDAO();

        BusDTO toBeCreated = new BusDTO(0, "EE111EE", null, null, 10);
        BusDTO toFailCreation = new BusDTO(0, "EE111EE", null, null, 20);
        BusDTO toFailValidationOfLicensePlate = new BusDTO(0, "A", null, null, 10);

        try {
            controller.doSaveBus(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            controller.doSaveBus(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}

        try{
            controller.doSaveBus(toFailValidationOfLicensePlate);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}


        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Bus original = EntityFactoryFacade.getEntity(toBeCreated);
        busDAO.setSession(session);
        Bus read = busDAO.read("licensePlate", original.getLicensePlate()).get(0);
        assertEquals(read, original);

        session.close();
    }
}
