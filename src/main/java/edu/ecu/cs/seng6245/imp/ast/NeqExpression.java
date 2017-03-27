package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing a not equal (e.g., 1 != 2) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class NeqExpression extends Expression {
    /** The left operand */
    private final Expression left;
    
    /** The right operand */
    private final Expression right;

    /**
     * Create a new not equal to expression.
     * 
     * @param left the left operand.
     * @param right the right operand.
     */
    public NeqExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Interpret this not equals expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing 0 when left equals
     *          right, the {@link ImpValue} representing 1 when left and
     *          right are comparable but not equal, or throws an
     *          {@link InvalidOperationException} if the expressions are
     *          not of types that can be compared
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing 0 when left equals
     *         right, the {@link ImpValue} representing 1 when left and
     *         right are comparable but not equal
     *
     * @throws InvalidOperationException if the expressions are not of types
     *         that can be compared
     */
    public ImpValue interpret(Environment env) {
        ImpValue leftValue = left.interpret(env);
        ImpValue rightValue = right.interpret(env);
        return leftValue.notEqual(rightValue);
    }

}
