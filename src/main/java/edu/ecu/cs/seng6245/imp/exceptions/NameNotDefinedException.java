package edu.ecu.cs.seng6245.imp.exceptions;

/**
 * Exception that is triggered when an undefined name is used in
 * an IMP program.
 * 
 * @author Mark Hills
 * @version 1.0
 *
 */
public class NameNotDefinedException extends RuntimeException {
    private static final long serialVersionUID = -2636480395122903649L;

    /** The undefined name */
    private final String undefinedName;
    
    /**
     * Create a new NameNotDefinedException.
     * 
     * @param msg            the exception message
     * @param undefinedName  the name that wasn't defined
     */
    public NameNotDefinedException(String msg, String undefinedName) {
        super(msg);
        this.undefinedName = undefinedName;
    }

    /**
     * Get the undefined name responsible for the exception being thrown.
     * 
     * @return the undefined name
     */
    public String getUndefinedName() {
        return undefinedName;
    }
    
}
