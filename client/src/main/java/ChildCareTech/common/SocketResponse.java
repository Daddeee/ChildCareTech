package ChildCareTech.common;

import java.io.Serializable;

/**
 * This class encapsulates a socket response.
 */
public class SocketResponse implements Serializable {
    /**
     * The type of the response.
     */
    public SocketResponseType responseType;
    /**
     * The returned value. Unlike {@link SocketRequest} there can only be a single return value, like RMI function calls.
     */
    public Serializable returnValue;

    /**
     * @param responseType the type of the response.
     * @param returnValue the returned value.
     */
    public SocketResponse(SocketResponseType responseType, Serializable returnValue){
        this.responseType = responseType;
        this.returnValue = returnValue;
    }
}
