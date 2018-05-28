package ChildCareTech.utils.exceptions;

/**
 * This exception is thrown when an entity doesn't pass some validations constraints in DAO operations.
 */
public class ValidationFailedException extends Exception{
    public ValidationFailedException() { super(); }
    public ValidationFailedException(String s) { super(s); }
}
