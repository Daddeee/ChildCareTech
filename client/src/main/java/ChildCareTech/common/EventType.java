package ChildCareTech.common;

/**
 * The possible type of an event, based on the occurrence holding it.
 */
public enum EventType {
    /**
     * Daily entry or exit events.
     */
    DAILY,
    /**
     * Entry or exit events associated to a meal.
     */
    MEAL,
    /**
     * Entry or exit events associated to a route.
     */
    TRIP
}
