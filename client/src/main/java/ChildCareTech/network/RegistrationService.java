package ChildCareTech.network;

/**
 * The classes implementing this interface must provide logic to register a new User and handle registration errors.
 */
public interface RegistrationService {
    /**
     * Register a new User on the server with the given credentials
     *
     * @param userName the new user name.
     * @param password the new password.
     * @return false if the registration has failed, true otherwise.
     */
    boolean registerAttempt(String userName, String password);

    /**
     * @return the registration error message, if any.
     */
    String getRegistrationErrorMessage();
}
