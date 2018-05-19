package ChildCareTech.network.socket;

import ChildCareTech.Client;
import ChildCareTech.common.SocketRequest;
import ChildCareTech.common.SocketRequestType;
import ChildCareTech.common.SocketResponse;
import ChildCareTech.common.SocketResponseType;
import ChildCareTech.network.RegistrationService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketRegistrationService implements RegistrationService {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String registrationErrorMessage;

    public SocketRegistrationService() throws IOException {
        this.socket = new Socket("127.0.0.1", 1199);
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.registrationErrorMessage = "";
    }

    @Override
    public boolean registerAttempt(String userName, String password) {
        boolean status = false;

        SocketRequest request = new SocketRequest(SocketRequestType.REGISTER, userName, password);
        SocketResponse response;

        try {
            out.writeObject(request);
            response = (SocketResponse) in.readObject();

            if(response.responseType.equals(SocketResponseType.FAIL)){
                registrationErrorMessage = ((Exception) response.returnValue).getMessage();
            } else {
                status = true;
            }
        } catch (IOException | ClassNotFoundException e){
            registrationErrorMessage = e.getMessage();
            e.printStackTrace();
        }

        Client.setRegistrationService(null);
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public String getRegistrationErrorMessage() {
        return registrationErrorMessage;
    }
}
