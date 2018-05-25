package ChildCareTech.controller;

import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.CurrentWorkDayService;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class WorkDayController {
    public WorkDayController() {}

    public WorkDayDTO doGetWorkDay(LocalDate date) {
        WorkDayDAO workDayDAO = new WorkDayDAO();
        WorkDay result = null;

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            result = workDayDAO.read("date", date).get(0);
            workDayDAO.initializeLazyRelations(result);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return DTOFactoryFacade.getDTO(result);
    }

    public LocalDate doGetMaxSavedDate() {
        LocalDate result = null;
        Transaction tx = null;
        WorkDayDAO workDayDAO = new WorkDayDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            result = workDayDAO.getMaxPersistentDate();
            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public LocalDate doGetMinSavedDate() {
        LocalDate result = null;
        Transaction tx = null;
        WorkDayDAO workDayDAO = new WorkDayDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            result = workDayDAO.getMinPersistentDate();
            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public WorkDayDTO doGetCurrentWorkDay() {
        return DTOFactoryFacade.getDTO(CurrentWorkDayService.getCurrent());
    }

    public void doTriggerDailyScheduling() throws RemoteException {
        WorkDayDAO workDayDAO = new WorkDayDAO();
        WorkDay reloadedToday = null;

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            reloadedToday = workDayDAO.read(CurrentWorkDayService.getCurrent());

            workDayDAO.initializeLazyRelations(reloadedToday);

            tx.commit();

            CurrentWorkDayService.setCurrent(reloadedToday);
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TODAY);
    }
}
