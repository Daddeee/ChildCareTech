package ChildCareTech.common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface PersonDTO extends Remote {
    public String getFirstName() throws RemoteException;
    public String getLastName() throws RemoteException;
    public String getFiscalCode() throws RemoteException;
    public LocalDate getBirthDate() throws RemoteException;
    public Sex getSex() throws RemoteException;
    public String getAddress() throws RemoteException;
    public String getPhoneNumber() throws RemoteException;

    public void setFirstName(String firstName) throws RemoteException;
    public void setLastName(String lastName) throws RemoteException;
    public void setFiscalCode(String fiscalCode) throws RemoteException;
    public void setBirthDate(LocalDate birthDate) throws RemoteException;
    public void setSex(Sex sex) throws RemoteException;
    public void setAddress(String address) throws RemoteException;
    public void setPhoneNumber(String phoneNumber) throws RemoteException;
}
