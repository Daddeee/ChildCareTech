package ChildCareTechTest.controller.BusController;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.BusController;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.entities.Bus;
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
 * Black-box test of {@link BusController#doRemoveBus(BusDTO)} .
 */
public class DoRemoveBusTest extends AbstractControllerActionTest {
    private BusController busController = new BusController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>passing an entity not stored in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        BusDAO busDAO = new BusDAO();

        Bus bus = new Bus("AA111AA", 10);
        BusDTO busDTO = DTOFactoryFacade.getDTO(bus);

        busController.doRemoveBus(busDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        busDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            busDAO.create(bus);
            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        busDAO.setSession(session1);
        assertEquals(busDAO.read(bus), bus);
        session1.close();

        busDTO = DTOFactoryFacade.getDTO(bus);
        busController.doRemoveBus(busDTO);

        Session session2 = HibernateSessionFactoryUtil.getInstance().openSession();
        busDAO.setSession(session2);
        assertNull(busDAO.read(bus));
        session2.close();

    }
}