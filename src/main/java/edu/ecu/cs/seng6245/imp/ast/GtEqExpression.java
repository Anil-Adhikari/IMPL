package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing a greater than or equal to (e.g., 1 &gt;= 2) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class GtEqExpression extends Expression {
    /** The left operand */
    private final Expression left;
    
    /** The right operand */
    private final Expression right;

    /**
     * Create a new greater than or equal to expression.
     * 
     * @param left the left operand.
     * @param right the right operand.
     */
    public GtEqExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Interpret this greater than or equals expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing 1 when left &gt;=
     *          right, the {@link ImpValue} representing 0 when left &lt;
     *          right, or throws {@link InvalidOperationException} if the
     *          expressions are not of types that can be compared
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing 1 when left &gt;=
     *         right, the {@link ImpValue} representing 0 when left &lt;
     *         right
     *
     * @throws InvalidOperationException if the expressions are not of types
     *         that can be compared
     */
    public ImpValue interpret(Environment env) {
        ImpValue leftValue = left.interpret(env);
        ImpValue rightValue = right.interpret(env);
        return leftValue.greaterThanOrEqual(rightValue);
    }
}
