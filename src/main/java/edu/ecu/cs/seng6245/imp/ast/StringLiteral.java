package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a string literal (e.g., "hello") in IMP.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class StringLiteral extends Literal {
    /** The string value of the literal */
    private final String strValue;

    /**
     * Create a new string literal.
     *
     * @param s the string for the string literal.
     */
    public StringLiteral(String s) {
        strValue = s;
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
        return ImpValueFactory.getValueFactory().makeStr(strValue);
    }
}
