package ChildCareTech.common;

import java.io.Serializable;

public class SocketResponse implements Serializable {
    public SocketResponseType responseType;
    public Serializable returnValue;

    public SocketResponse(SocketResponseType responseType, Serializable returnValue){
        this.responseType = responseType;
        this.returnValue = returnValue;
    }
}
