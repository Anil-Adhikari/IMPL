package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * AST Node representing a subset of expression in IMP.
 *
 * @author Mark Hills
 * @version 1.0
 */
public class SubsetOfExpression extends Expression {
    /** The left-hand set operand */
    private final Expression leftSet;

    /** The right-hand set operand */
    private final Expression rightSet;

    /**
     * Create a new subsetOf expression.
     *
     * @param a the left operand
     * @param b the right operand
     */
    public SubsetOfExpression(Expression a, Expression b) {
        leftSet = a;
        rightSet = b;
    }

    /**
     * Interpret the setsetOf expression. This will return {@code true}
     * when the left-hand operand is a subset of the right-hand operand
     * and {@code false} when it is not, e.g.,
     * {@code {1,2,3} subseOf {1,2,3,4}} is {@code {1,2,3,4}}.
     *
     * @param env the current environment
     * @return true when the left-hand set is a subset of the right-hand set, false otherwise
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue left = leftSet.interpret(env);
        ImpValue right = rightSet.interpret(env);
        return left.subsetOf(right);
    }
}
