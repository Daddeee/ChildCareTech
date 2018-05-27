package ChildCareTech.controller;

import ChildCareTech.services.MainWindowService;

/**
 * This interface should be implemented by controllers that need to access their own mainWindowService attribute
 * and manage their own stage.
 */
public interface MainWindowControllerInterface {
    void setMainWindowService(MainWindowService mainWindowService);
}
