package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;

public class IndexExpression extends Expression {
    /** The expression generating a value with members */
    private final Expression container;

    /** The index being looked up */
    private final Expression index;
    
    public IndexExpression(Expression container, Expression idx) {
        this.container = container;
        this.index = idx;
    }

    /**
     * Interpret the index expression, returning the resulting value.
     *
     * <p>
     * EFFECTS: Returns the element at the given index from the list as
     *          an {@link ImpValue}, throws a {@link ListIndexException}
     *          if the list is empty or the given index is out of bounds
     *          (the first list index is 1, not 0), throws an
     *          {@link InvalidOperationException} if the expressions
     *          being index into cannot be used as a list
     * </p>
     *
     * @param env the current environment
     *
     * @return the element at the given index from the list as an {@link ImpValue}
     *
     * @throws InvalidOperationException if the expression being indexed into
     *         cannot be used as a list
     * @throws ListIndexException if the list is empty or the given index is
     *         out of bounds (the first list index is 1, not 0)
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue lhs = container.interpret(env);
        ImpValue rhs = index.interpret(env);
        
        return lhs.get(rhs);
    }
}
