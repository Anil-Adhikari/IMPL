package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;
import java.util.Iterator;

import beaver.Symbol;

/**
 * Represents a list of individual {@link Number} items. Note
 * that we inherit {@link beaver.Symbol} and not {@link ASTNode}
 * since this isn't intended to be an independent node in the AST.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class NumberList extends Symbol implements Iterable<Number> {
    private ArrayList<Number> Numbers;
    
    /**
     * Create an empty Number list.
     */
    public NumberList() {
        Numbers = new ArrayList<>();
    }
    
    /**
     * Create a Number list with a single Number.
     * 
     * @param s    the Number that will be placed in the list
     */
    public NumberList(Number s) {
        this();
        Numbers.add(s);
    }
    
    /**
     * Create a Number list with the given head and tail.
     * 
     * @param s  the head of the new Number list
     * @param ss the tail of the new Number list
     */
    public NumberList(Number s, NumberList ss) {
        this();
        Numbers.add(s);
        Numbers.addAll(ss.Numbers);
    }

    /**
     * Return an iterator over the Numbers in the list.
     */
    @Override
    public Iterator<Number> iterator() {
        return Numbers.iterator();
    }
}
