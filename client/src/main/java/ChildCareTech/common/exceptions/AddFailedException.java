package ChildCareTech.common.exceptions;

/**
 * This exception is thrown when a create action on an entity goes wrong.
 */
public class AddFailedException extends Exception {
    public AddFailedException() { super(); }
    public AddFailedException(String s) { super(s); }
}
