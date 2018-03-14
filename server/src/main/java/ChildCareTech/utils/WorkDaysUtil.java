package ChildCareTech.utils;

import java.time.LocalDate;

public class WorkDaysUtil {

    public static void init(LocalDate lastDate) throws IllegalArgumentException{

        LocalDate curDate = LocalDate.now();

        if(lastDate.isBefore(curDate)) {
            System.err.println("Argument date is invalid");
            throw new IllegalArgumentException();
        }

        while(curDate.isBefore(lastDate) || curDate.isEqual(lastDate)) {
            WorkDaysUtil.insertDate(curDate);
            curDate = curDate.plusDays(1);
        }
    }

    public static void insertDate(LocalDate date) { //exceptions?
        //insert date into database
    }

}
