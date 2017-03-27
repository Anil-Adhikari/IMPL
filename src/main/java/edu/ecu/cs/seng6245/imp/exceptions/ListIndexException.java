package edu.ecu.cs.seng6245.imp.exceptions;

/**
 * Exception that is triggered when an out of bounds list index is used
 * on a list.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public class ListIndexException extends RuntimeException {

    private static final long serialVersionUID = -8337331431132556579L;

    private final int givenIndex;
    private final int listSize;
    
    public ListIndexException(String msg, int index, int size) {
        super(msg);
        givenIndex = index;
        listSize = size;
    }
    
    public String toString() {
        return "Invalid index, current list size is " + listSize + ", requested index is " + givenIndex + ", " + this.getMessage();
    }
}
