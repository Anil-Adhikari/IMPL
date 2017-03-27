package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class NumericSetLiteral extends Literal {
    /** The list of numbers given between the set braces */
    private final NumberList literalList;
    
    /**
     * Create a new literal set.
     * 
     * @param nl the list of numbers making up the set
     */
    public NumericSetLiteral(NumberList nl) {
        literalList = nl;
    }

    /**
     * Interpret the set literal, returning the value of the literal.
     *
     * <p>
     * EFFECTS: Returns the value of the set literal, as an {@link ImpValue}
     * </p>
     *
     * @param env the current environment
     *
     * @return the value of the set literal, as an {@link ImpValue}
     */
    @Override
    public ImpValue interpret(Environment env) {
        ArrayList<Integer> al = new ArrayList<>();
        
        for (Number n : literalList) {
            al.add(n.intValue());
        }
        
        return ImpValueFactory.getValueFactory().makeIntegerSet(al);
    }

}
