package ChildCareTechTest.controller.KidController;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.KidController;
import ChildCareTech.model.DAO.KidDAO;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Kid;
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
 * Black-box test of {@link KidController#doUpdateKid(KidDTO)}.
 */
public class DoUpdateKidTest extends AbstractControllerActionTest {
    private KidController kidController = new KidController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        KidDAO kidDAO = new KidDAO();
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person ped = new Person("0000000000000000", "pediatra", "pediatra", LocalDate.now(), Sex.MALE, "", "1");
        Pediatrist pediatrist = new Pediatrist(ped);

        Person p1 = new Person("0000000000000001", "Kido", "Da creare", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("0000000000000002", "Kido", "Aggiornato", LocalDate.now(), Sex.MALE, "", "1");
        Kid saved = new Kid(p1, pediatrist, null, pediatrist);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        personDAO.setSession(session);
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(ped);
            personDAO.create(p1);
            personDAO.create(p2);
            pediatristDAO.create(pediatrist);
            kidDAO.create(saved);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PersonDTO newPerson = DTOFactoryFacade.getDTO(p2);
        KidDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setPerson(newPerson);

        try {
            kidController.doUpdateKid(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        KidDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setPerson(DTOFactoryFacade.getDTO(p2));

        failUpdated.getPerson().setPhoneNumber("1");
        failUpdated.getPerson().setFiscalCode("0000");
        try{
            kidController.doUpdateKid(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        kidDAO.setSession(session1);
        personDAO.setSession(session1);

        Kid read = kidDAO.read(updated.getId());
        KidDTO readDTO = DTOFactoryFacade.getDTO(read);
        assertEquals(readDTO, updated);

        session1.close();
    }
}