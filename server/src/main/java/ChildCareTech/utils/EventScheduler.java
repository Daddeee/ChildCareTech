package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.DTO.factories.EventDTOFactory;

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

        List<Event> todayEvents = new ArrayList<>(current.getEvents());

        try {
            RemoteEventObservable.getInstance().setPlannedEvents(todayEvents);
        } catch(RemoteException e){
            e.printStackTrace();
        }

        for(Event e : todayEvents){
            Runnable startEvent = new Runnable() {
                @Override
                public void run() {
                    try{
                        RemoteEventObservable.getInstance().changeEventStatus(e, EventStatus.OPEN);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            Runnable stopEvent = new Runnable() {
                @Override
                public void run() {
                    try{
                        RemoteEventObservable.getInstance().changeEventStatus(e, EventStatus.CLOSED);
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
    };

    public EventScheduler() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dailyTime = now.withHour(0).withMinute(30);
        Duration duration;
        long initialDelay;

        if(now.isAfter(dailyTime)){
            dailyTime = dailyTime.plusDays(1);

            duration = Duration.between(now, dailyTime);
            initialDelay = duration.getSeconds();

            firstTimeJobScheduled = scheduler.schedule(dailyScheduling, 10, TimeUnit.SECONDS);
            jobScheduled = scheduler.scheduleAtFixedRate(dailyScheduling, initialDelay, 24*60*60, TimeUnit.SECONDS);
        } else {
            duration = Duration.between(now, dailyTime);
            initialDelay = duration.getSeconds();

            firstTimeJobScheduled = null;
            jobScheduled = scheduler.scheduleAtFixedRate(dailyScheduling, initialDelay, 24*60*60, TimeUnit.SECONDS);
        }
    }
}
