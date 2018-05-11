package ChildCareTech.controller;

import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;
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

            result = workDayDAO.read("date", date.toString()).get(0);
            workDayDAO.initializeLazyRelations(result);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return DTOFactory.getDTO(result);
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

    public void doTriggerDailyScheduling() throws RemoteException {
        WorkDayDAO workDayDAO = new WorkDayDAO();
        WorkDay reloadedToday = null;

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        workDayDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            reloadedToday = workDayDAO.read(RemoteEventObservable.getInstance().getToday());

            workDayDAO.initializeLazyRelations(reloadedToday);

            tx.commit();
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        RemoteEventObservable.getInstance().setDay(reloadedToday);
    }
}
