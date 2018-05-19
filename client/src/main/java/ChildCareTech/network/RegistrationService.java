package ChildCareTech.network;

public interface RegistrationService {
    boolean registerAttempt(String userName, String password);
    String getRegistrationErrorMessage();
}
