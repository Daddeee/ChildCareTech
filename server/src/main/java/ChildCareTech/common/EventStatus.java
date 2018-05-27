package ChildCareTech.common;

/**
 * Possible status for an event or anything that has a life cycle (eg: {@link ChildCareTech.model.entities.Meal Meal}, {@link ChildCareTech.model.entities.Trip Trip}, ...).
 */
public enum EventStatus {
    /**
     * The event has not started yet.
     */
    WAIT,
    /**
     * The event is in progress.
     */
    OPEN,
    /**
     * The event is terminated.
     */
    CLOSED
}
