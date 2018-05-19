package ChildCareTech.common;

import java.io.Serializable;

public class SocketRequest implements Serializable {
    public SocketRequestType requestType;
    public Serializable[] params;

    public SocketRequest(SocketRequestType requestType, Serializable ... params){
        this.requestType = requestType;
        this.params = params;
    }
}
