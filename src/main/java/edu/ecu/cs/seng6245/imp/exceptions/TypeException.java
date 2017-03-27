package edu.ecu.cs.seng6245.imp.exceptions;

/**
 * Exception that is triggered when an operation is performed
 * on incompatible types, such as a comparison between a boolean
 * and an integer value.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public class TypeException extends RuntimeException {
    private static final long serialVersionUID = 3675057881865379979L;

    public TypeException() {
        super();
    }

    public TypeException(String msg) {
        super(msg);
    }
}
