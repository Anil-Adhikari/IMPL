package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;

public class InExpression extends Expression {
    /** The element being checked for membership */
    private final Expression element;
    
    /** The expression generating a value with members */
    private final Expression container;
    
    /**
     * Create a new in expression.
     * 
     * @param element the element to look up in the container
     * @param container the container to look up the element in
     */
    public InExpression(Expression element, Expression container) {
        this.element = element;
        this.container = container;
    }

    /**
     * Interpret the in expression, returning a true or false value.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} for 0 when the element on the
     *          left is not in the container on the right, returns the
     *          {@link ImpValue} for 1 when it is, throws an
     *          {@link InvalidOperationException} if the expressions cannot
     *          be used in membership checks
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} for 0 when the element on the
     *          left is not in the container on the right, the
     *          {@link ImpValue} for 1 when it is
     *
     * @throws InvalidOperationException if the expressions cannot be used
     *         in membership checks
     */
    @Override
    public ImpValue interpret(Environment env) {
        ImpValue lhs = element.interpret(env);
        ImpValue rhs = container.interpret(env);
        
        return lhs.in(rhs);
    }

}
