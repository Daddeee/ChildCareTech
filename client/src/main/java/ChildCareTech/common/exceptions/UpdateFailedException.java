package ChildCareTech.common.exceptions;
/**
 * This exception is thrown when an update action on an entity goes wrong.
 */
public class UpdateFailedException extends Exception {
    public UpdateFailedException() {super();}
    public UpdateFailedException(String s) {super(s);}
}
