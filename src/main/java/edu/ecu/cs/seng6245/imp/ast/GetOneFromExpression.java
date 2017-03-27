package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.EmptySetException;

public class GetOneFromExpression extends Expression {
    /** The container being accessed */
    private final Expression container;
    
    public GetOneFromExpression(Expression container) {
        this.container = container;
    }

    /**
     * Interpret the get one from expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns an arbitrary element from the set as an {@link ImpValue},
     * or throws an {@link EmptySetException} if the set is empty
     * </p>
     *
     * @param env the current environment
     *
     * @return an arbitrary element from the set as an {@link ImpValue}
     *
     * @throws EmptySetException if the set is empty
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue res = container.interpret(env);
        return res.choose();
    }

}
