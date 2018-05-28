package ChildCareTechTest.controller.AdultController;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.controller.AdultController;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Black-box test of {@link AdultController#doGetAllAdultsExclusive()} .
 */
public class DoGetAllAdultsExclusiveTest extends AbstractControllerActionTest {
    private AdultController adultController = new AdultController();

    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>calling when there are no entities in the database.</li>
     * </ul>
     */
    @Override
    public void testAction() {
        PersonDAO personDAO = new PersonDAO();
        AdultDAO adultDAO = new AdultDAO();

        Person p1 = new Person("0000000000000001", "Osvaldo", "Sozio", LocalDate.parse("1980-10-20"), Sex.MALE, "via Carducci 23", "3334536789");
        Person p2 = new Person("0000000000000002", "Mattia", "Secomandi", LocalDate.parse("1980-12-10"), Sex.MALE, "via Rossi 57", "3331234567");
        Person p3 = new Person("0000000000000003", "Francesco", "Alberti", LocalDate.parse("1996-05-07"), Sex.FEMALE, "via Caccialupo 12", "1234567890");
        Person p4 = new Person("0000000000000004", "Elia", "Bettola", LocalDate.parse("1996-10-05"), Sex.MALE, "via Martiri del lavoro 41", "1234567890");

        Adult a1 = new Adult(p1);
        Pediatrist a2 = new Pediatrist(p2);
        Staff a3 = new Staff(p3);
        Supplier a4 = new Supplier(p4);

        List<AdultDTO> expectedEmpty = adultController.doGetAllAdultsExclusive();
        assert(expectedEmpty.isEmpty());

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        adultDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            personDAO.create(p1);
            personDAO.create(p2);
            personDAO.create(p3);
            personDAO.create(p4);

            adultDAO.create(a1);
            adultDAO.create(a2);
            adultDAO.create(a3);
            adultDAO.create(a4);

            tx.commit();
        } catch (Exception e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        }

        List<AdultDTO> result = adultController.doGetAllAdultsExclusive();
        assert(result.size() == 1 && result.contains(DTOFactoryFacade.getDTO(a1)));
    }
}
