package ChildCareTech.controller;

import javafx.event.ActionEvent;

/**
 * This interface should be implemented by those controllers that contain a refreshable node such as tableViews, listViews etc
 * and need to have accessors windows.
 * Provides refresh and update notification requests to accessors window controllers.
 */

public interface TableWindowControllerInterface {
    //void addButtonAction(ActionEvent event);
    //void editButtonAction(ActionEvent event);
    //void detailsButtonAction(ActionEvent event);
    //void deleteButtonAction(ActionEvent event);

    /**
     * This method should manage to refresh the controller's refreshable nodes.
     * Should be called also by outside the controller (for example by accessor windows' controllers).
     */
    void refreshTable();

    /**
     * This mehod should manage the actions to take in case of an update notification.
     * Should be called also by outside the controller (for example by accessor windows' controllers).
     */
    void notifyUpdate();
}
