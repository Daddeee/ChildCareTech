package ChildCareTech.common.exceptions;
/**
 * This exception is thrown when a user registration request does not meet the requirements.
 */
public class RegistrationFailedException extends Exception {
    public RegistrationFailedException() {
        super();
    }

    public RegistrationFailedException(String s) {
        super(s);
    }
}
