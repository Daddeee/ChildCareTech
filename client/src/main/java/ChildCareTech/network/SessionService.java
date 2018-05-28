package ChildCareTech.network;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSessionFacade;

/**
 * The classes implementing this interface must provide logic to login/logout a User and handle login errors.
 */
public interface SessionService {
    /**
     * Attempt a login with the given credentials.
     *
     * @param userName the user name to login.
     * @param password the corresponding password.
     */
    void loginAttempt(String userName, String password);

    /**
     * Try to log out the current user.
     */
    void logoutAttempt();

    /**
     * @return the current active server session.
     */
    UserSessionFacade getSession();

    /**
     * @return the RemoteEventObserver associated with the current active session.
     */
    RemoteEventObserver getObserver();

    /**
     * @return true if no  active session is present, false otherwise.
     */
    boolean isNull();

    /**
     * @return the login error message, if any.
     */
    String getLoginErrorMessage();
}
