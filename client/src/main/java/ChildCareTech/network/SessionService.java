package ChildCareTech.network;

import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.UserSession;

public interface SessionService {
    void loginAttempt(String userName, String password);

    void logoutAttempt();

    UserSession getSession();

    RemoteEventObserver getObserver();

    boolean isNull();

    String getLoginErrorMessage();
}
