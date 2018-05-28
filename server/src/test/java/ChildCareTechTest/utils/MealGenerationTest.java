package ChildCareTechTest.utils;

import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.MealsGenerationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.fail;

/**
 * Testing {@link MealsGenerationUtil#generateMeals(Canteen, List, List)} method and all his limit cases. See method's doc for further specifications.
 */
public class MealGenerationTest {
    private SessionFactory sessionFactory;
    private Session session = null;

    @Test
    public void testMealGeneration(){
        Canteen canteen = new Canteen("Test", null);
        CanteenDAO canteenDAO = new CanteenDAO();
        WorkDayDAO workDayDAO = new WorkDayDAO();

        List<LocalTime> correctEntryTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("13:00"), LocalTime.parse("16:00")));
        List<LocalTime> correctExitTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("14:00"), LocalTime.parse("17:00")));

        List<LocalTime> differentSizeEntryTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("13:00"), LocalTime.parse("16:00"), LocalTime.now()));
        List<LocalTime> differentSizeExitTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("14:00"), LocalTime.parse("17:00")));

        List<LocalTime> overlappingEntryTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("13:00"), LocalTime.parse("13:30"), LocalTime.now()));
        List<LocalTime> overlappingExitTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("14:00"), LocalTime.parse("14:30")));

        List<LocalTime> not10MinEntryTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("13:00"), LocalTime.parse("16:00"), LocalTime.now()));
        List<LocalTime> not10MinsExitTimes = new ArrayList<>(Arrays.asList(LocalTime.parse("12:00"), LocalTime.parse("16:05")));

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            canteenDAO.create(canteen);

            LocalDate date = LocalDate.now();
            for(int i = 0; i < 20; i++){
                boolean isHoliday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
                workDayDAO.create(new WorkDay(date.plusDays(i), LocalTime.MIN, LocalTime.NOON, isHoliday));
            }

            tx.commit();
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        try{
            MealsGenerationUtil.generateMeals(canteen, overlappingEntryTimes, overlappingExitTimes);
            fail("Not failing with overlapping times");
        } catch(AddFailedException e){}

        try{
            MealsGenerationUtil.generateMeals(canteen, differentSizeEntryTimes, differentSizeExitTimes);
            fail("Not failing with different size times");
        } catch(AddFailedException e){}

        try{
            MealsGenerationUtil.generateMeals(canteen, not10MinEntryTimes, not10MinsExitTimes);
            fail("Not failing with intervals too short");
        } catch(AddFailedException e){}

        try {
            MealsGenerationUtil.generateMeals(canteen, correctEntryTimes, correctExitTimes);
        } catch (AddFailedException e) {
            fail(e.getMessage());
        }

        Session newSession = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(newSession);
        canteenDAO.setSession(newSession);
        tx = null;
        try {
            tx = newSession.beginTransaction();

            for(WorkDay w : workDayDAO.readAll()){
                if(w.isHoliday() && w.getMeals() != null && w.getMeals().size() > 0) fail("Generating in holidays");
                if(w.getMeals() == null || w.getMeals().size() != 2) fail("Not generated 2 meals per work day");

                int i = 0;

                List<Meal> meals = new ArrayList<>(w.getMeals());
                if(meals.get(0).getEntryEvent().getBeginTime().isAfter(meals.get(1).getEntryEvent().getBeginTime()))
                    Collections.swap(meals, 0, 1);

                for(Meal m : meals){
                    if(!m.getCanteen().equals(canteen)) fail("Not setting canteen");
                    if(!m.getWorkDay().equals(w)) fail("Not setting workday");

                    if(!m.getEntryEvent().getBeginTime().equals(correctEntryTimes.get(i))) fail("Entry time " + (i + 1) + " meal not correct");
                    if(!m.getExitEvent().getBeginTime().equals(correctExitTimes.get(i))) fail("Exit time " + (i + 1) + " meal not correct");

                    i++;
                }
            }

            Canteen c = canteenDAO.read(canteen);
            assert(c.getMeals().size() == 40);

            tx.commit();
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            newSession.close();
        }
    }

    /**
     * Configure and start the testing database.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // setup the session factory
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    /**
     * After every test, the database is dropped.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("drop database test").executeUpdate();
        tx.commit();
        session.close();

        sessionFactory.close();
    }
}
