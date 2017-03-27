package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing an expression statement in IMP (a statement
 * that is just an expression, ended with a semicolon, like 3+4;)
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class ExprStatement extends Statement {
    /** The expression in the expression statement. */
    private final Expression expr;
    
    /**
     * Create a new expression statement.
     * 
     * @param expr the expression made into a statement.
     */
    public ExprStatement(Expression expr) {
        this.expr = expr;
    }

    /**
     * Interpret the contained expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} computed by the expression.
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} computed by the expression
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue res = expr.interpret(env);
        return res; 
    }

}
