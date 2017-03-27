package edu.ecu.cs.seng6245.imp.exceptions;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * Exception that is triggered when an operation is attempted on a value
 * that does not support it. For instance, you cannot take the size of
 * an integer, or index into a set.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public class InvalidOperationException extends RuntimeException {
    private static final long serialVersionUID = -3027400501788367765L;
    
    private ArrayList<ImpValue> operands = new ArrayList<>();
    private final String operation;
    
    public InvalidOperationException(String op, ImpValue v) {
        operation = op;
        operands.add(v);
    }

    public InvalidOperationException(String op, ImpValue l, ImpValue r) {
        operation = op;
        operands.add(l);
        operands.add(r);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot call operation ").append(operation).append(" on ");
        if (operands.size() == 1) {
            sb.append(" an operand of type ");
        } else {
            sb.append(" operands of the following types: ");
        }
        boolean firstItem = true;
        for (ImpValue iv : operands) {
            if (!firstItem) {
                sb.append(", ");
            } else {
                firstItem = false;
            }
            sb.append(iv.type());
        }
        return sb.toString();
    }
}
