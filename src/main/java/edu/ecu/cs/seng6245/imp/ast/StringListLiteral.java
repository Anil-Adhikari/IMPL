package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class StringListLiteral extends Literal {
    /** The list of strings given between the list braces */
    private final StringList literalList;


    /**
     * Create a new list literal.
     *
     * @param sl the list of strings making up the list
     */
    public StringListLiteral(StringList sl) {
        literalList = sl;
    }

    /**
     * Evaluate the ListLiteral, returning a new list with the given elements.
     *
     * @param env the current environment
     *
     * @return a value representing the list
     */
    @Override
    public ImpValue interpret(Environment env) {
        ArrayList<String> al = new ArrayList<>();

        for (String s : literalList) {
            al.add(s);
        }

        return ImpValueFactory.getValueFactory().makeStringList(al);
    }

}
