package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.NameNotDefinedException;

/**
 * AST Node representing an identifier (e.g., x) expression in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class IdExpression extends Expression {
    /** The identifier */
    private final String id;

    /**
     * Create a new identifier expression.
     * 
     * @param id the identifier.
     */
    public IdExpression(String id) {
        this.id = id;
    }

    /**
     * Interpret this identifier expression, returning the resulting value.
     *
     * <p>
     * REQUIRES: env is not null
     * </p>
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} currently assigned to id in env,
     *          or throws a {@link NameNotDefinedException} if the id is not
     *          present in the environment
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} currently assigned to id in env
     *
     * @throws NameNotDefinedException if the id is not in the environment
     */
    public ImpValue interpret(Environment env) {
        return env.getValue(id);
    }

}
