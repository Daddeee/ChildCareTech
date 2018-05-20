package ChildCareTech.utils;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class EventScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ScheduledFuture<?> firstTimeJobScheduled;
    private final ScheduledFuture<?> jobScheduled;
    private final Runnable dailyScheduling = () -> {
        WorkDay current = CurrentWorkDayService.getCurrent();

        try {
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TODAY);
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TRIP);
        } catch(RemoteException e){
            e.printStackTrace();
        }

        for(Event e : current.getEvents()){
            Runnable startEvent = new Runnable() {
                @Override
                public void run() {
                    try{
                        CurrentWorkDayService.changeEventStatus(e, EventStatus.OPEN);
                        RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.EVENT);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            Runnable stopEvent = new Runnable() {
                @Override
                public void run() {
                    try{
                        CurrentWorkDayService.changeEventStatus(e, EventStatus.CLOSED);
                        RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.EVENT);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
            LocalTime now = LocalTime.now();
            Duration duration = Duration.between(now, e.getBeginTime());

            if(duration.getSeconds() > 0)
                scheduler.schedule(startEvent, duration.getSeconds(),TimeUnit.SECONDS);

            duration = Duration.between(now, e.getEndTime());
            if(duration.getSeconds() > 0)
                scheduler.schedule(stopEvent, duration.getSeconds(), TimeUnit.SECONDS);

        }

        System.out.println("[SCHEDULER] Daily scheduling terminated.");
    };

    public  EventScheduler(){
        Object lock = WorkDaysGenerationUtil.getLock();
        synchronized(lock) {
            try {
                while (CurrentWorkDayService.getCurrent() == null)
                    lock.wait();
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }

        System.out.println("[SCHEDULER] automatic scheduler ready. Start scheduling.");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dailyTime = now.withHour(0).withMinute(30);
        Duration duration;
        long initialDelay;

        if(now.isAfter(dailyTime)){
            dailyTime = dailyTime.plusDays(1);

            duration = Duration.between(now, dailyTime);
            initialDelay = duration.getSeconds();

            firstTimeJobScheduled = scheduler.schedule(dailyScheduling, 5, TimeUnit.SECONDS);
            jobScheduled = scheduler.scheduleAtFixedRate(dailyScheduling, initialDelay, 24*60*60, TimeUnit.SECONDS);
        } else {
            duration = Duration.between(now, dailyTime);
            initialDelay = duration.getSeconds();

            firstTimeJobScheduled = null;
            jobScheduled = scheduler.scheduleAtFixedRate(dailyScheduling, initialDelay, 24*60*60, TimeUnit.SECONDS);
        }
    }
}
