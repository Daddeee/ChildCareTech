package ChildCareTechTest.controller.StaffController;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.StaffController;
import ChildCareTech.model.DAO.StaffDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link StaffController#doUpdateStaffMember(StaffDTO)}.
 */
public class DoUpdateStaffMemberTest extends AbstractControllerActionTest {
    private StaffController staffController = new StaffController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        StaffDAO staffDAO = new StaffDAO();

        Person p1 = new Person("0000000000000001", "Staff", "Da creare", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("0000000000000002", "Staff", "Aggiornato", LocalDate.now(), Sex.MALE, "", "1");
        Staff saved = new Staff(p1);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(p1);
            personDAO.create(p2);
            staffDAO.create(saved);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PersonDTO newPerson = DTOFactoryFacade.getDTO(p2);
        StaffDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setPerson(newPerson);

        try {
            staffController.doUpdateStaffMember(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        StaffDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setPerson(DTOFactoryFacade.getDTO(p2));

        failUpdated.getPerson().setPhoneNumber("");
        try{
            staffController.doUpdateStaffMember(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        failUpdated.getPerson().setPhoneNumber("1");
        failUpdated.getPerson().setFiscalCode("0000");
        try{
            staffController.doUpdateStaffMember(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        staffDAO.setSession(session1);
        personDAO.setSession(session1);

        Staff read = staffDAO.read(updated.getId());
        StaffDTO readDTO = DTOFactoryFacade.getDTO(read);
        assertEquals(readDTO, updated);

        session1.close();
    }
}