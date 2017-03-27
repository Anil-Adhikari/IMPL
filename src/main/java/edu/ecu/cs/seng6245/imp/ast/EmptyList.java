package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class EmptyList extends Literal {

    /**
     * Interprets the empty list literal, returning the {@link ImpValue}
     * representing the empty list.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the empty list
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the empty list
     */
    @Override
    public ImpValue interpret(Environment env) {
        return ImpValueFactory.getValueFactory().makeList();
    }

}
