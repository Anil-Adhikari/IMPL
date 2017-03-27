package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing an if statement in IMP.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class IfStatement extends Statement {
    /** The condition for the if statement */
    private final Expression cond;
    
    /** The true branch body */
    private final Statement trueBody;
    
    /** The false branch body */
    private final Statement falseBody;
    
    /**
     * Create a new if statement.
     * 
     * @param c  the if condition
     * @param tc the statement to run when the condition is true
     * @param fc the statement to run when the condition is false
     */
    public IfStatement(Expression c, Statement tc, Statement fc) {
        cond = c;
        trueBody = tc;
        falseBody = fc;
    }

    /**
     * Interpret this if statement, returning the resulting value.
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
     * EFFECTS: Executes the true statement when the condition is true
     *          (not zero) or the false statement otherwise. env is modified
     *          if it is modified by any of the statements inside the true
     *          or false branches of the if. The {@link ImpValue} representing
     *          void is returned.
     * </p>
     *
     * @param env the current environment
     *
     * @return The {@link ImpValue} representing void is returned.
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue condResult = cond.interpret(env);
        if (condResult.isTrue()) {
            trueBody.interpret(env);
        } else {
            falseBody.interpret(env);
        }
        return ImpValueFactory.getValueFactory().makeVoid();
    }

}
