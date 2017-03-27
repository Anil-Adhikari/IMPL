package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class StringSetLiteral extends Literal {
    /** The list of strings given between the set braces */
    private final StringList literalList;

    /**
     * Create a new literal set.
     *
     * @param sl the list of strings making up the set
     */
    public StringSetLiteral(StringList sl) {
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
        ArrayList<String> al = new ArrayList<>();

        for (String n : literalList) {
            al.add(n);
        }

        return ImpValueFactory.getValueFactory().makeStringSet(al);
    }

}
