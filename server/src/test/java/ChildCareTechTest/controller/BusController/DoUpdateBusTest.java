package ChildCareTechTest.controller.BusController;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.BusController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link BusController#doUpdateBus(BusDTO)}.
 */
public class DoUpdateBusTest extends AbstractControllerActionTest {
    private BusController busController = new BusController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        BusDAO busDAO = new BusDAO();
        Bus saved = new Bus("AA111AA", 10);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            busDAO.create(saved);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        BusDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setCapacity(2);

        try {
            busController.doUpdateBus(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        BusDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setCapacity(-2);
        try{
            busController.doUpdateBus(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        failUpdated.setCapacity(2);
        failUpdated.setLicensePlate("invalid");
        try{
            busController.doUpdateBus(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        busDAO.setSession(session1);
        Bus read = busDAO.read(updated.getId());
        BusDTO readDTO = DTOFactoryFacade.getDTO(read);
        assert (readDTO.getCapacity() == updated.getCapacity());

        session1.close();
    }
}