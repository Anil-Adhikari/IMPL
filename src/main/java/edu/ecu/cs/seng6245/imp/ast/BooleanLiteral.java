package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a boolean literal (e.g., true) in IMP.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class BooleanLiteral extends Literal {
    /** The boolean value of the literal */
    private final Boolean boolValue;

    /**
     * Create a new boolean literal.
     *
     * @param b the boolean for the boolean literal
     */
    public BooleanLiteral(Boolean b) {
        boolValue = b;
    }

    /**
     * Interpret the boolean literal, returning the value of
     * the literal.
     *
     * @param env the current environment
     *
     * @return the value of the boolean literal
     */
    @Override
    public ImpValue interpret(Environment env) {
        return ImpValueFactory.getValueFactory().makeBool(boolValue);
    }
}
