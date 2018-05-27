package ChildCareTech.common.exceptions;

/**
 * This exception is thrown when the device's requested camera is alredy in use.
 */
public class CameraBusyException extends Exception {
    public CameraBusyException() { super(); }
    public CameraBusyException(String s) { super(s); }

}
