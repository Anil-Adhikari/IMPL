package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;

/**
 * AST Node representing a division (e.g., 4 / 2) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class DivExpression extends Expression {
    /** The left operand */
    private final Expression left;
    
    /** The right operand */
    private final Expression right;
    
    /**
     * Create a new division expression.
     * 
     * @param left the left operand.
     * @param right the right operand.
     */
    public DivExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Interpret this division expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the result of
     *          left / right, or throws an {@link InvalidOperationException}
     *          if the expressions are not of types that can be divided
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the result of
     *         left / right
     *
     * @throws InvalidOperationException if the expressions are not of
     *         types that can be divided
     */
    @Override
    public ImpValue interpret(Environment env) {
        return left.interpret(env).div(right.interpret(env));
    }

}
