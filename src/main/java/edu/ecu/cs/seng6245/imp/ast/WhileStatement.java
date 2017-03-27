package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a while statement in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class WhileStatement extends Statement {
    /** The while condition */
    private final Expression cond;
    
    /** The while body */
    private final Statement body;
    
    /**
     * Create a new while statement.
     * 
     * @param c the condition for the while loop
     * @param b the body of the loop
     */
    public WhileStatement(Expression c, Statement b) {
        cond = c;
        body = b;
    }

    /**
     * Interpret the while statement, which will run the body of the while
     * until the condition becomes false.
     *
     * @param env the current environment
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
     * EFFECTS: Returns the {@link ImpValue} representing void. env is
     *          modified if it is modified by any of the statements inside
     *          the while loop. The loop condition is executed, and if it
     *          is true the body of the loop is executed. This process is
     *          repeated until the condition becomes false. If the condition
     *          is never true, the loop body never executes.
     * </p>
     *
     * @return the {@link ImpValue} representing void
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue condResult = cond.interpret(env);
        while (condResult.isTrue()) {
            body.interpret(env);
            condResult = cond.interpret(env);
        }
        return ImpValueFactory.getValueFactory().makeVoid();
    }

}