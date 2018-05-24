package ChildCareTech.network;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSessionFacade;

public interface SessionService {
    void loginAttempt(String userName, String password);

    void logoutAttempt();

    UserSessionFacade getSession();

    RemoteEventObserver getObserver();

    boolean isNull();

    String getLoginErrorMessage();
}
