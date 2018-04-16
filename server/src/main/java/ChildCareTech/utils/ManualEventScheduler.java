package ChildCareTech.utils;

import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualEventScheduler implements Runnable{
    /*
    * Command line event scheduler. Testing purposes.
    */

    private Scanner in;

    public ManualEventScheduler() {
        in = new Scanner(System.in);

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

        while(true){
            System.out.print("> ");
            cmd = in.nextLine();
            in.reset();
            if(cmd.equals("fire")) fireEvent();
            else if(cmd.equals("quit")) break;
        }
    }

    private void fireEvent() throws RemoteException{
        WorkDay w = CurrentWorkDayService.getCurrent();
        List<Event> events = new ArrayList<>(w.getEvents());

        for(int i = 0; i < events.size(); i++)
            System.out.println(i + ": "
                    + events.get(i).getName()
                    + " " + events.get(i).getBeginTime().toString()
                    + " - " + events.get(i).getEndTime().toString()
            );

        int selection = in.nextInt();
        in.nextLine();

        /*RemoteEventObservable.getInstance().setNextEvent(
                DTOFactory.getDTO(events.get(selection))
        );*/
    }
}
