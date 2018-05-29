package ChildCareTechTest.controller.PersonController;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.PersonController;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link  PersonController#doGetPerson(String)}.
 */
public class DoGetPersonTest extends AbstractControllerActionTest {
    private PersonController personController = new PersonController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling with an unsaved date.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        String fiscalCode = "0000000000000001";

        Person person = new Person(fiscalCode, "nome", "cognome", LocalDate.now(), Sex.MALE, "indirizzo", "1");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(person);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        PersonDTO personDTO = personController.doGetPerson(fiscalCode);
        Person result = EntityFactoryFacade.getEntity(personDTO);

        assertEquals(result, person);

    }
}
