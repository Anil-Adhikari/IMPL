package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing an intersection expression in IMP.
 *
 * @author Mark Hills
 * @version 1.0
 */
public class IntersectionExpression extends Expression {
    /** The left-hand set operand */
    private final Expression leftSet;

    /** The right-hand set operand */
    private final Expression rightSet;

    /**
     * Create a new intersection expression.
     *
     * @param a the left operand
     * @param b the right operand
     */
    public IntersectionExpression(Expression a, Expression b) {
        leftSet = a;
        rightSet = b;
    }

    /**
     * Interpret the intersection expression. This will return the
     * elements common to both sets, e.g., {@code {1,2,3} & {2,3,4}}
     * is {@code {2,3}}.
     *
     * @param env the current environment
     * @return the intersection of the two sets.
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue left = leftSet.interpret(env);
        ImpValue right = rightSet.interpret(env);
        return left.intersection(right);
    }
}
