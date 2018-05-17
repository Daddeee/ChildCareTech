package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.RouteDAO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;

public class RouteController {
    public RouteController() {}

    public void doRemoveRoute(RouteDTO routeDTO) {
        RouteDAO routeDAO = new RouteDAO();
        Route route = DTOEntityAssembler.getEntity(routeDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        routeDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            routeDAO.delete(route);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doUpdateRouteEvent(RouteDTO routeDTO) throws UpdateFailedException, RemoteException {
        RouteDAO routeDAO = new RouteDAO();
        EventDAO eventDAO = new EventDAO();
        Route route = DTOEntityAssembler.getEntity(routeDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        routeDAO.setSession(session);
        eventDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(route.getDepartureEvent() != null)
                if(route.getDepartureEvent().getId() == 0) {
                    eventDAO.create(route.getDepartureEvent());
                } else {
                    eventDAO.update(route.getDepartureEvent());
                }

            if(route.getArrivalEvent() != null)
                if(route.getArrivalEvent().getId() == 0) {
                    eventDAO.create(route.getArrivalEvent());
                } else {
                    eventDAO.update(route.getArrivalEvent());
                }

            routeDAO.update(route);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }

        new WorkDayController().doTriggerDailyScheduling();
    }
}
