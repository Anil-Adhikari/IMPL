package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class BooleanListLiteral extends Literal {
    /** The list of booleans given between the list braces */
    private final BooleanList literalList;


    /**
     * Create a new list literal.
     *
     * @param bl the list of booleans making up the list
     */
    public BooleanListLiteral(BooleanList bl) {
        literalList = bl;
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
        ArrayList<Boolean> al = new ArrayList<>();

        for (Boolean n : literalList) {
            al.add(n);
        }

        return ImpValueFactory.getValueFactory().makeBooleanList(al);
    }

}
