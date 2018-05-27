package ChildCareTech.common;

/**
 * Possible status for an event or anything that has a life cycle (eg: Meal, Trip, ...).
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
