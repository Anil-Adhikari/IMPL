package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing an integer literal (e.g., 5) in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class IntegerLiteral extends Literal {
    /** The integer value of the literal */
    private final Integer intValue;
    
    /**
     * Create a new integer literal.
     * 
     * @param iv the integer for the integer literal.
     */
    public IntegerLiteral(Integer iv) {
        intValue = iv;
    }

    /**
     * Interpret the integer literal, returning the value of the literal.
     *
     * <p>
     * EFFECTS: Returns the value of the integer literal, as an {@link ImpValue}
     * </p>
     *
     * @param env the current environment
     *
     * @return the value of the integer literal, as an {@link ImpValue}
     */
    @Override
    public ImpValue interpret(Environment env) {
        return ImpValueFactory.getValueFactory().makeInt(intValue);
    }
}
