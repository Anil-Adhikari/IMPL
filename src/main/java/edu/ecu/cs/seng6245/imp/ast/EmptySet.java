package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class EmptySet extends Literal {

    /**
     * Interprets the empty set literal, returning the {@link ImpValue}
     * representing the empty set.
     *
     * <p>
     * EFFECTS: Returns the {@link ImpValue} representing the empty set
     * </p>
     *
     * @param env the current environment
     *
     * @return the {@link ImpValue} representing the empty set
     */
    @Override
    public ImpValue interpret(Environment env) {
        return ImpValueFactory.getValueFactory().makeSet();
    }

}
