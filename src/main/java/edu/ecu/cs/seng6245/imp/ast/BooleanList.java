package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;
import java.util.Iterator;

import beaver.Symbol;

/**
 * Represents a list of individual {@link Boolean} items. Note
 * that we inherit {@link beaver.Symbol} and not {@link ASTNode}
 * since this isn't intended to be an independent node in the AST.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class BooleanList extends Symbol implements Iterable<Boolean> {
    private ArrayList<Boolean> Booleans;

    /**
     * Create an empty Boolean list.
     */
    public BooleanList() {
        Booleans = new ArrayList<>();
    }

    /**
     * Create a Boolean list with a single Boolean.
     *
     * @param s	the Boolean that will be placed in the list
     */
    public BooleanList(Boolean s) {
        this();
        Booleans.add(s);
    }

    /**
     * Create a Boolean list with the given head and tail.
     *
     * @param s  the head of the new Boolean list
     * @param ss the tail of the new Boolean list
     */
    public BooleanList(Boolean s, BooleanList ss) {
        this();
        Booleans.add(s);
        Booleans.addAll(ss.Booleans);
    }

    /**
     * Return an iterator over the Booleans in the list.
     */
    @Override
    public Iterator<Boolean> iterator() {
        return Booleans.iterator();
    }
}
