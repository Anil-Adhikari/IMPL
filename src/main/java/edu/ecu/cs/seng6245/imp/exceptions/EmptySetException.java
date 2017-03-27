package edu.ecu.cs.seng6245.imp.exceptions;

/**
 * Exception that is triggered when an operation that requires a
 * non-empty set, such as get one from, is used on an empty set.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public class EmptySetException extends RuntimeException {
    private static final long serialVersionUID = 2082887003070488823L;

    public EmptySetException(String msg) {
        super(msg);
    }
}
