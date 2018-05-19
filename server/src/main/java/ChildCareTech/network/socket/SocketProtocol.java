package ChildCareTech.network.socket;

import ChildCareTech.common.SocketRequest;
import ChildCareTech.common.SocketRequestType;
import ChildCareTech.common.SocketResponse;
import ChildCareTech.common.SocketResponseType;
import ChildCareTech.controller.UserController;
import ChildCareTech.model.entities.User;

import java.util.HashMap;

public class SocketProtocol {
    private HashMap<SocketRequestType, SocketRequestHandler> methodMap;
    private SocketUserSession socketUserSession;

    public SocketProtocol(SocketUserSession socketUserSession) {
        this.methodMap = new HashMap<>();
        this.socketUserSession = socketUserSession;
        loadProtocol();
    }

    public SocketResponse handleRequest(SocketRequest socketRequest){
        return methodMap.get(socketRequest.requestType).handle(socketRequest);
    }

    private SocketResponse handleLogin(SocketRequest request){
        SocketResponse response;

        try{
            String userName = (String) request.params[0];
            String password = (String) request.params[1];

            socketUserSession.setUser(UserController.getUser(userName, password));
            UserController.storeSession(socketUserSession.getUser(), new Object());

            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleLogout(SocketRequest request){
        SocketResponse response;

        try {
            UserController.removeSession(socketUserSession.getUser());
            socketUserSession.close();
            response = new SocketResponse(SocketResponseType.SUCCESS, null);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private SocketResponse handleRegister(SocketRequest request){
        SocketResponse response;
        boolean returnValue;

        try {
            String userName = (String) request.params[0];
            String password = (String) request.params[1];

            returnValue = UserController.registerUser(userName, password);

            response = new SocketResponse(SocketResponseType.SUCCESS, returnValue);
        } catch (Exception e){
            response = new SocketResponse(SocketResponseType.FAIL, e);
        }

        return response;
    }

    private void loadProtocol(){
        this.methodMap.put(SocketRequestType.LOGIN, this::handleLogin);
        this.methodMap.put(SocketRequestType.LOGOUT, this::handleLogout);
        this.methodMap.put(SocketRequestType.REGISTER, this::handleRegister);
    }

    public interface SocketRequestHandler {
        SocketResponse handle(SocketRequest request);
    }
}
