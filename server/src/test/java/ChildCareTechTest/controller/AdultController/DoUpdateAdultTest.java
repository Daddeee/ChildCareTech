package ChildCareTechTest.controller.AdultController;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.controller.AdultController;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Adult;
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
 * Black-box test of {@link AdultController#doUpdateAdult(AdultDTO)}.
 */
public class DoUpdateAdultTest extends AbstractControllerActionTest {
    private AdultController adultController = new AdultController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>updating an entity with invalid values</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        AdultDAO adultDAO = new AdultDAO();

        Person p1 = new Person("0000000000000001", "Adulto", "Da creare", LocalDate.now(), Sex.MALE, "", "1");
        Person p2 = new Person("0000000000000002", "Adulto", "Aggiornato", LocalDate.now(), Sex.MALE, "", "1");
        Adult saved = new Adult(p1);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        adultDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(p1);
            personDAO.create(p2);
            adultDAO.create(saved);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        PersonDTO newPerson = DTOFactoryFacade.getDTO(p2);
        AdultDTO updated = DTOFactoryFacade.getDTO(saved);
        updated.setPerson(newPerson);

        try {
            adultController.doUpdateAdult(updated);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        AdultDTO failUpdated = DTOFactoryFacade.getDTO(saved);
        failUpdated.setPerson(DTOFactoryFacade.getDTO(p2));

        failUpdated.getPerson().setPhoneNumber("");
        try{
            adultController.doUpdateAdult(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        failUpdated.getPerson().setPhoneNumber("1");
        failUpdated.getPerson().setFiscalCode("0000");
        try{
            adultController.doUpdateAdult(failUpdated);
            fail("Exception not thrown");
        } catch (UpdateFailedException e) {}

        Session session1 = HibernateSessionFactoryUtil.getInstance().openSession();
        adultDAO.setSession(session1);
        personDAO.setSession(session1);

        Adult read = adultDAO.read(updated.getId());
        AdultDTO readDTO = DTOFactoryFacade.getDTO(read);
        assertEquals(readDTO, updated);

        session1.close();
    }
}
