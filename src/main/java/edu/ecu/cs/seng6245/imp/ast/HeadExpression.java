package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.EmptyListException;

/**
 * AST Node representing a head expression in IMP.
 *
 * @author Mark Hills
 * @version 1.0
 */
public class HeadExpression extends Expression {
    /** The list expression we are taking the head of */
    private final Expression listExpression;

    /**
     * Create a new head expression.
     *
     * @param a the operand, which should yield a list
     */
    public HeadExpression(Expression a) {
        listExpression = a;
    }

    /**
     * Interpret the head expression. If the list has at least one
     * element, e.g., {@code [1,2]}, this will return the first element,
     * e.g. {@code 1}. If the list is empty, e.g. {@code []}, this will
     * throw an {@link EmptyListException}.
     *
     * @param env the current environment
     * @return the head of the list, or {@link EmptyListException} if the
     *         list is empty
     * @throws EmptyListException when the list is empty
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue iv = listExpression.interpret(env);
        return iv.head();
    }
}
