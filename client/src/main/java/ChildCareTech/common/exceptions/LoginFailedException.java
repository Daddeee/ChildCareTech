package ChildCareTech.common.exceptions;

/**
 * This exception is thrown when a login attempt goes wrong.
 */
public class LoginFailedException extends Exception {
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String s) {
        super(s);
    }
}
