package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class BooleanSetLiteral extends Literal {
    /** The list of booleans given between the set braces */
    private final BooleanList literalList;

    /**
     * Create a new literal set.
     *
     * @param sl the list of booleans making up the set
     */
    public BooleanSetLiteral(BooleanList sl) {
        literalList = sl;
    }

    /**
     * Evaluate the SetLiteral, returning a new set with the given elements.
     *
     * @param env the current environment
     *
     * @return a value representing the set
     */
    @Override
    public ImpValue interpret(Environment env) {
        ArrayList<Boolean> al = new ArrayList<>();

        for (Boolean n : literalList) {
            al.add(n);
        }

        return ImpValueFactory.getValueFactory().makeBooleanSet(al);
    }

}
