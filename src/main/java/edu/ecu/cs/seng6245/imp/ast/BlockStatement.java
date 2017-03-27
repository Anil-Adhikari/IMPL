package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a block (begin/end) statement in IMP.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class BlockStatement extends Statement {
    /** The list of statements in the block. */
    private final StatementList sList;
    
    /**
     * Create a new block statement.
     * 
     * @param ss the list of statements inside the block.
     */
    public BlockStatement(StatementList ss) {
        sList = ss;
    }

    /**
     * Interpret the block statement by interpreting each statement in
     * the block, returning an {@link ImpValue} representing void.
     *
     * <p>
     * MODIFIES: env is not null
     * </p>
     *
     * <p>
     * MODIFIES: env
     * </p>
     *
     * <p>
     * EFFECTS: Interprets each statement in the block, and returns
     *          the {@link ImpValue} representing void. env is modified
     *          if it is modified by any of the statements inside the
     *          block of statements.
     * </p>
     *
     * @param env the current environment
     *
     * @return An {@link ImpValue} representing void.
     */
    @Override
    public ImpValue interpret(Environment env) {
        for (Statement s : sList) {
            s.interpret(env);
        }
        
        return ImpValueFactory.getValueFactory().makeVoid();
    }

}
