package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.RouteDAO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that operate with Route entities.
 */
public class RouteController {
    public RouteController() {}

    /**
     * See {@link UserSessionFacade#removeRoute(RouteDTO)}
     *
     * @param routeDTO
     */
    public void doRemoveRoute(RouteDTO routeDTO) {
        RouteDAO routeDAO = new RouteDAO();
        Route route = EntityFactoryFacade.getEntity(routeDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        routeDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            routeDAO.delete(route);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TRIP);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#updateRouteEvent(RouteDTO)}
     *
     * @param routeDTO
     * @throws UpdateFailedException
     * @throws RemoteException
     */
    public void doUpdateRouteEvent(RouteDTO routeDTO) throws UpdateFailedException, RemoteException {
        RouteDAO routeDAO = new RouteDAO();
        EventDAO eventDAO = new EventDAO();
        Route route = EntityFactoryFacade.getEntity(routeDTO);

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

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TRIP);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }

        new WorkDayController().doTriggerDailyScheduling();
    }
}
