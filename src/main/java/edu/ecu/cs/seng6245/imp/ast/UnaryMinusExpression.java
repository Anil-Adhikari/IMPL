package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a unary minus (e.g., -1) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class UnaryMinusExpression extends Expression {
    /** The operand. */
    private final Expression operand;
    
    /**
     * Create a new unary minus expression.
     * 
     * @param e the operand.
     */
    public UnaryMinusExpression(Expression e) {
        operand = e;
    }

    /**
     * Interpret this negation expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the result of
     *          negating the operand, or an {@link InvalidOperationException}
     *          if the operand is not of a type that can be negated
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the result of
     *          negating the operand
     *
     * @throws InvalidOperationException if the operand is not of a type
     *         that can be negated
     */
    public ImpValue interpret(Environment env) {
        return operand.interpret(env).times(ImpValueFactory.getValueFactory().makeInt(-1));
    }

}
