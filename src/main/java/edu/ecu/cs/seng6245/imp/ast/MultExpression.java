package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing a multiplication (e.g., 1 * 2) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class MultExpression extends Expression {
    /** The left operand. */
    private final Expression left;
    
    /** The right operand. */
    private final Expression right;
    
    /**
     * Create a new multiplication expression.
     * 
     * @param left the left operand.
     * @param right the right operand.
     */
    public MultExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Interpret this multiplication expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the result of
     *          left * right, or an {@link InvalidOperationException}
     *          if the expressions are not of types that can be added
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the result of
     *         left * right
     *
     * @throws InvalidOperationException if the expressions are not of
     *         types that can be multiplied
     */
    public ImpValue interpret(Environment env) {
        return left.interpret(env).times(right.interpret(env));
    }
    
}
