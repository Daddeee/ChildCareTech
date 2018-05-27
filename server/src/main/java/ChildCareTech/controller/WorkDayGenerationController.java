package ChildCareTech.controller;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.utils.Settings;
import ChildCareTech.utils.WorkDaysGenerationUtil;

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that manage WorkDay initial generation.
 */
public class WorkDayGenerationController {
    public WorkDayGenerationController() {}

    /**
     * See {@link UserSessionFacade#generateDays(DayGenerationSettingsDTO)}
     *
     * @param settings
     */
    public void doGenerateDays(DayGenerationSettingsDTO settings) {
        WorkDaysGenerationUtil wdu = new WorkDaysGenerationUtil(settings);
        wdu.generateDays();
    }

    /**
     * See {@link UserSessionFacade#isFirstEverStartup()}
     *
     * @return
     */
    public boolean doIsFirstEverStartup() {
        return Boolean.parseBoolean(Settings.getProperty("firstRun"));
    }

    /**
     * See {@link UserSessionFacade#setFirstEverStartup(boolean)}
     *
     * @param value
     */
    public void doSetFirstEverStartup(boolean value) {
        Object lock = WorkDaysGenerationUtil.getLock();
        synchronized (lock){
            Settings.storeProperty("firstRun", Boolean.toString(value));
            lock.notifyAll();
        }
    }

}
