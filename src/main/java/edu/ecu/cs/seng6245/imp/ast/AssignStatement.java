package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing an assignment statement in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class AssignStatement extends Statement {
    /** The identifier being assigned into */
    private final String id;
    
    /** The expression on the right-hand side of the assignment */
    private final Expression expr;
    
    /**
     * Create a new assignment statement.
     * 
     * @param id   the identifier being assigned into
     * @param expr the expression on the right-hand side of the assignment
     */
    public AssignStatement(String id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    /**
     * Interpret this assignment statement, returning void.
     *
     * <p>
     * REQUIRES: env is not null
     * </p>
     *
     * <p>
     * MODIFIES: env
     * </p>
     *
     * <p>
     * EFFECTS: env is updated so id refers to the {@link ImpValue} of
     *          the right-hand side
     * </p>
     *
     * @param env the current environment
     *
     * @return An {@link ImpValue} representing void.
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue res = expr.interpret(env);
        env.setValue(id, res);
        return ImpValueFactory.getValueFactory().makeVoid();
    }

}
