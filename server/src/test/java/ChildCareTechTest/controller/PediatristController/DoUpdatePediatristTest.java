package ChildCareTechTest.controller.PediatristController;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.PediatristController;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Pediatrist;
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
 * Black-box test of {@link PediatristController#doUpdatePediatrist(PediatristDTO)}.
 */
public class DoUpdatePediatristTest extends AbstractControllerActionTest {
    private PediatristController pediatristController = new PediatristController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person p1 = new Person("0000000000000001", "Pediatrist", "Da creare", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("0000000000000002", "Pediatrist", "Aggiornato", LocalDate.now(), Sex.MALE, "", "1");
        Pediatrist saved = new Pediatrist(p1);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        pediatristDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(p1);
            personDAO.create(p2);
            pediatristDAO.create(saved);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PersonDTO newPerson = DTOFactoryFacade.getDTO(p2);
        PediatristDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setPerson(newPerson);

        try {
            pediatristController.doUpdatePediatrist(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        PediatristDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setPerson(DTOFactoryFacade.getDTO(p2));

        failUpdated.getPerson().setPhoneNumber("");
        try{
            pediatristController.doUpdatePediatrist(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        failUpdated.getPerson().setPhoneNumber("1");
        failUpdated.getPerson().setFiscalCode("0000");
        try{
            pediatristController.doUpdatePediatrist(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        pediatristDAO.setSession(session1);
        personDAO.setSession(session1);

        Pediatrist read = pediatristDAO.read(updated.getId());
        PediatristDTO readDTO = DTOFactoryFacade.getDTO(read);
        assertEquals(readDTO, updated);

        session1.close();
    }
}