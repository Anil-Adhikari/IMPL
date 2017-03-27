package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing a literal (e.g., 5) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class LiteralExpression extends Expression {
    /** The value of the literal */
    private final Literal lit;
    
    /**
     * Create a new literal expression.
     * 
     * @param l the value of the literal
     */
    public LiteralExpression(Literal l) {
        lit = l;
    }

    /**
     * Interpret this literal expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the literal in
     *          the expression
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the literal in the expression
     */
    public ImpValue interpret(Environment env) {
        return lit.interpret(env);
    }

}
