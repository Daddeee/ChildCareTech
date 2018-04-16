package ChildCareTech.utils;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualEventScheduler implements Runnable{
    /*
    * Command line event scheduler. Testing purposes.
    */

    private Scanner in;
    private WorkDay planned;
    private WorkDay toPlan;
    private WorkDayDAO workDayDAO;
    private EventDAO eventDAO;

    public ManualEventScheduler() {
        in = new Scanner(System.in);
        planned = null;
        toPlan = CurrentWorkDayService.getCurrent();
        workDayDAO = new WorkDayDAO();
        eventDAO = new EventDAO();
        Thread backgroundThread = new Thread(this);
        backgroundThread.start();
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
                default:
                    break;
            }
        }
    }

    private void openEvent(){
        List<Event> plannedEvents = new ArrayList<>(RemoteEventObservable.getInstance().getPlannedEvents());
        plannedEvents.removeIf(event -> !event.getEventStatus().equals(EventStatus.WAIT));

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }

        int selection = in.nextInt();
        in.nextLine();

        try {
            RemoteEventObservable.getInstance().changeEventStatus(plannedEvents.get(selection), EventStatus.OPEN);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void closeEvent(){
        List<Event> plannedEvents = new ArrayList<>(RemoteEventObservable.getInstance().getPlannedEvents());
        plannedEvents.removeIf(event -> !event.getEventStatus().equals(EventStatus.OPEN));

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }

        int selection = in.nextInt();
        in.nextLine();

        try {
            RemoteEventObservable.getInstance().changeEventStatus(plannedEvents.get(selection), EventStatus.CLOSED);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void listPlanned(){
        List<Event> plannedEvents = RemoteEventObservable.getInstance().getPlannedEvents();

        for(int i = 0; i < plannedEvents.size(); i++){
            System.out.println(i + ": " + plannedEvents.get(i).toString());
        }
    }

    private void runDailyScheduling(){
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();

        workDayDAO.setSession(session);
        try {
            tx = session.beginTransaction();

            planned = toPlan;
            RemoteEventObservable.getInstance().setDay(planned);

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
