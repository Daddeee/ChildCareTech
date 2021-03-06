package ChildCareTech.utils;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provides a command line utility to mimic a time-based scheduler.
 * <p>
 * Can be launched with the command "scheduler" from the server console
 * Possible commands:
 * <ul>
 *     <li>daily: steps to "tomorrow", changing the current work day</li>
 *     <li>planned: displays all today's events</li>
 *     <li>open: select and open an event</li>
 *     <li>close: select and close an event</li>
 *     <li>quit: quits the scheduler</li>
 * </ul>
 */
public class ManualEventScheduler implements Runnable{
    private Scanner in;
    private WorkDay planned;
    private WorkDay toPlan;
    private WorkDayDAO workDayDAO;
    private EventDAO eventDAO;

    public ManualEventScheduler() {
        in = new Scanner(System.in);
        workDayDAO = new WorkDayDAO();
        eventDAO = new EventDAO();

        Thread backgroundThread = new Thread(this);
        backgroundThread.start();

        planned = null;
        toPlan = CurrentWorkDayService.getCurrent();
    }

    @Override
    public void run() {
        String initCmd;

        while(true){
            initCmd = in.nextLine();
            if(initCmd.equals("scheduler"))
                try{
                    showScheduleMenu();
                } catch (RemoteException e){
                    e.printStackTrace();
                }
        }
    }

    private void showScheduleMenu() throws RemoteException{
        System.out.println("<-- Manual event scheduling -->\n");
        String cmd;
        boolean quit = false;

        while(!quit){
            System.out.print("> ");
            cmd = in.nextLine();
            in.reset();
            switch(cmd){
                case "daily":
                    runDailyScheduling();
                    break;
                case "planned":
                    listPlanned();
                    break;
                case "quit":
                    quit = true;
                    break;
                case "open":
                    openEvent();
                    break;
                case "close":
                    closeEvent();
                    break;
                case "next":
                    System.out.println(toPlan.getDate().toString());
                    break;
                case "reset":
                    reset();
                    break;
                default:
                    break;
            }
        }
    }

    private void openEvent(){
        List<Event> plannedEvents = new ArrayList<>(CurrentWorkDayService.getCurrent().getEvents());
        plannedEvents.removeIf(event -> !event.getEventStatus().equals(EventStatus.WAIT));

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }

        int selection = in.nextInt();
        in.nextLine();

        try {
            CurrentWorkDayService.changeEventStatus(plannedEvents.get(selection), EventStatus.OPEN);
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.EVENT);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void closeEvent(){
        List<Event> plannedEvents = new ArrayList<>(CurrentWorkDayService.getCurrent().getEvents());
        plannedEvents.removeIf(event -> !event.getEventStatus().equals(EventStatus.OPEN));

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }

        int selection = in.nextInt();
        in.nextLine();

        try {
            CurrentWorkDayService.changeEventStatus(plannedEvents.get(selection), EventStatus.CLOSED);
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.EVENT);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void listPlanned(){
        List<Event> plannedEvents = new ArrayList<>(CurrentWorkDayService.getCurrent().getEvents());

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }
    }

    private void reset(){
        planned = null;
        toPlan = CurrentWorkDayService.getCurrent();
        runDailyScheduling();
    }

    private void runDailyScheduling(){
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();

        workDayDAO.setSession(session);
        try {
            if(toPlan == null)
                toPlan = CurrentWorkDayService.getCurrent();

            tx = session.beginTransaction();

            planned = toPlan;
            CurrentWorkDayService.setCurrent(planned);
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TODAY);

            toPlan = workDayDAO.tomorrow(toPlan);
            workDayDAO.initializeLazyRelations(toPlan);
            for(Event e: toPlan.getEvents())
                eventDAO.initializeLazyRelations(e);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
