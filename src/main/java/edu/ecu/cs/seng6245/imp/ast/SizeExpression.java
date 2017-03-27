package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

public class SizeExpression extends Expression {
    /** The container being accessed */
    private final Expression container;

    public SizeExpression(Expression container) {
        this.container = container;
    }

    /**
     * Interpret the size expression, returning the size of the container
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the size of the
     *          container, or throws an {@link InvalidOperationException}
     *          if the expression is not a container
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} for the number representing the size
     *         of the container
     *
     * @throws InvalidOperationException if the expression is not a container
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue res = container.interpret(env);
        return res.size();
    }

}
