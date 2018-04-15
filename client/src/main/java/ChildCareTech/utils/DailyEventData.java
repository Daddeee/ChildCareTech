package ChildCareTech.utils;

import java.time.LocalTime;

public class DailyEventData {
    private String name;
    private String start;
    private String stop;
    private String status;

    public DailyEventData(String name, String start, String stop, String status){
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    public String getStatus() {
        return status;
    }

    public static String findStatus(LocalTime startEventTime, LocalTime stopEventTime) {
        LocalTime now = LocalTime.now();
        if(now.isBefore(startEventTime)) return "Pronto";
        if(now.isAfter(startEventTime) && now.isBefore(stopEventTime)) return "In corso";
        return "Terminato";
    }
}
