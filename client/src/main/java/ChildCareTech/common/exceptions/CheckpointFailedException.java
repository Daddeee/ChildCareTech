package ChildCareTech.common.exceptions;
/**
 * This exception is thrown when a checkpoint registration request does not meet the requirements.
 */
public class CheckpointFailedException extends Exception {
    public CheckpointFailedException() { super(); }
    public CheckpointFailedException(String s) { super(s); }
}
