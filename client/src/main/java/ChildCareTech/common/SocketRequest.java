package ChildCareTech.common;

import java.io.Serializable;

/**
 * This class encapsulates a socket request.
 */
public class SocketRequest implements Serializable {
    /**
     * The type of the request.
     */
    public SocketRequestType requestType;

    /**
     * The params passed in the request.
     */
    public Serializable[] params;

    /**
     * @param requestType the type of the request.
     * @param params the params passed in the request.
     */
    public SocketRequest(SocketRequestType requestType, Serializable ... params){
        this.requestType = requestType;
        this.params = params;
    }
}
