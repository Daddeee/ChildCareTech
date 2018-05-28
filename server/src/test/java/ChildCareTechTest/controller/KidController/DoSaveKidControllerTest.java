package ChildCareTechTest.controller.KidController;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
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
 * Black-box test of {@link KidController#doSaveKid(KidDTO)} (KidDTO)}.
 */
public class DoSaveKidControllerTest extends AbstractControllerActionTest {
    private KidController controller = new KidController();
    
    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating a kid when a person with the same fiscal code already exists</li>
     * </ul>
     */
    @Override
    public void testAction() {
        KidDAO kidDAO = new KidDAO();
        PersonDAO personDAO = new PersonDAO();
        PediatristDAO pediatristDAO = new PediatristDAO();

        Person storedFiscalCode = new Person("0000000000000000", "Prova", "Prova", LocalDate.now(), Sex.MALE, "", "0");
        Person pediatristPerson = new Person("0000000000000003", "Pediatra", "Pediatra", LocalDate.now(), Sex.MALE, "", "0");
        Pediatrist pediatrist = new Pediatrist(pediatristPerson);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(storedFiscalCode);
            personDAO.create(pediatristPerson);
            pediatristDAO.create(pediatrist);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PediatristDTO pediatristDTO = DTOFactoryFacade.getDTO(pediatrist);
        PersonDTO toBeCreatedPerson = new PersonDTO(0, "0000000000000001", "Nome", "Cognome", LocalDate.now(), Sex.MALE, "", "1", null);
        PersonDTO toFailCreationPerson = new PersonDTO(0, "0000000000000000", "Nome", "Cognome", LocalDate.now(), Sex.MALE, "", "1", null);

        KidDTO toBeCreated = new KidDTO(0, toBeCreatedPerson, pediatristDTO, null, pediatristDTO, null);
        KidDTO toFailCreation = new KidDTO(0, toFailCreationPerson, pediatristDTO, null, pediatristDTO, null);

        try {
            controller.doSaveKid(toBeCreated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            controller.doSaveKid(toFailCreation);
            fail("Exception not thrown");
        } catch (AddFailedException e) {}


        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        Kid original = EntityFactoryFacade.getEntity(toBeCreated);
        kidDAO.setSession(session1);
        personDAO.setSession(session1);
        Kid read = kidDAO.read("person_id", personDAO.read("fiscalCode", original.getPerson().getFiscalCode()).get(0).getId()).get(0);
        assertEquals(read, original);

        session1.close();
    }
}
