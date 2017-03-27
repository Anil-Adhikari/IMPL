package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * AST Node representing a for statement in IMP. A for statement
 * has syntax like:
 * <pre>
 * <code>
 * for i = 1 to 10 do n = n + i;
 * </code>
 * </pre>
 * Here, i is an identifier that will start with the value 1 and
 * take on every value from 1, 2, 3, ..., 9, 10. The body of the
 * for loop can either be a single statement or a block surrounded
 * with begin and end.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class ForStatement extends Statement {
    
    /** The for loop identifier */
    private final String id;
    
    /** The first iteration value of the loop */
    private final int start;
    
    /** The last iteration value of the loop */
    private final int end;
    
    /** The body of the loop */
    private final Statement body;
    
    public ForStatement(String i, Number s, Number f, Statement b) {
        id = i;
        start = s.intValue();
        end = f.intValue();
        body = b;
    }

    /**
     * Interpret the for statement by running the body a number of times
     * based on the given indexes, returning void.
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
     * EFFECTS: The loop is executed once for each index assigned into the
     *          index variable where that index is less than or equal to the
     *          upper number (the number after the keyword to). For instance,
     *          with</p>
     *          <pre><code>for i = 1 to 10 do n = i;</code></pre>
     *          <p>i will take on the values 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, in
     *          that order, while for</p>
     *          <pre><code>for i = 21 to 10 do n = i;</code></pre>
     *          <p>i will take on the value 21 but the loop will never execute
     *          since 21 is &gt; 10. env is updated with the final value of the
     *          loop variable (10 and 21 in these examples, respectively). The
     *          {@link ImpValue} representing void is returned.
     * </p>
     *
     * @param env the current environment
     *
     * @return An {@link ImpValue} representing void.
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValueFactory vf = ImpValueFactory.getValueFactory();
        env.setValue(id, vf.makeInt(start));
        for (int i = start; i <= end; ++i) {
            env.setValue(id, vf.makeInt(i));
            body.interpret(env);
        }
        return vf.makeVoid();
    }
}
