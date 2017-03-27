package edu.ecu.cs.seng6245.imp.exceptions;

/**
 * Exception that is triggered when an operation that requires a
 * non-empty list, such as head, is used on an empty list.
 *
 * @author Mark Hills
 * @version 1.1
 *
 */
public class EmptyListException extends RuntimeException {
    private static final long serialVersionUID = -5060192926175816107L;

    public EmptyListException(String msg) {
        super(msg);
    }
}
