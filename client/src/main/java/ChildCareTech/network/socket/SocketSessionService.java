package ChildCareTech.network.socket;

import ChildCareTech.Client;
import ChildCareTech.common.*;
import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.network.SessionService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketSessionService implements SessionService {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String loginErrorMessage;
    private SocketUserSession socketUserSession;

    public SocketSessionService() throws IOException {
        this.socket = new Socket("127.0.0.1", 1199);
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.loginErrorMessage = "";
        this.socketUserSession = null;
    }

    @Override
    public void loginAttempt(String userName, String password) {
        SocketRequest request = new SocketRequest(SocketRequestType.LOGIN, userName, password);
        SocketResponse response;
        try {
            out.writeObject(request);
            response = (SocketResponse) in.readObject();

            if(response.responseType.equals(SocketResponseType.FAIL)){
                loginErrorMessage = ((Exception) response.returnValue).getMessage();
            } else {
                socketUserSession = new SocketUserSession(socket, out, in);
            }
        } catch (IOException | ClassNotFoundException e){
            loginErrorMessage = e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void logoutAttempt() {
        SocketRequest request = new SocketRequest(SocketRequestType.LOGOUT);
        SocketResponse response;

        try{
            out.writeObject(request);

            loginErrorMessage = "";
        } catch (IOException e){
            e.printStackTrace();
        }

        socketUserSession = null;
        Client.setSessionService(null);

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserSession getSession() {
        return socketUserSession;
    }

    @Override
    public RemoteEventObserver getObserver() {
        return null;
    }

    @Override
    public boolean isNull() {
        return socketUserSession == null;
    }

    @Override
    public String getLoginErrorMessage() {
        return loginErrorMessage;
    }
}
