package ChildCareTech.controller;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.utils.Settings;
import ChildCareTech.utils.WorkDaysGenerationUtil;

public class WorkDayGenerationController {
    public WorkDayGenerationController() {}

    public void doGenerateDays(DayGenerationSettingsDTO settings) {
        WorkDaysGenerationUtil wdu = new WorkDaysGenerationUtil(settings);
        wdu.generateDays();
    }

    public boolean doIsFirstEverStartup() {
        return Boolean.parseBoolean(Settings.getProperty("firstRun"));
    }

    public void doSetFirstEverStartup(boolean value) {
        Object lock = WorkDaysGenerationUtil.getLock();
        synchronized (lock){
            Settings.storeProperty("firstRun", Boolean.toString(value));
            lock.notifyAll();
        }
    }

}
