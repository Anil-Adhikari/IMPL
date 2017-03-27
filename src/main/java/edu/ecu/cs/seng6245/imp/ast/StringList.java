package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;
import java.util.Iterator;

import beaver.Symbol;

/**
 * Represents a list of individual {@link String} items. Note
 * that we inherit {@link beaver.Symbol} and not {@link ASTNode}
 * since this isn't intended to be an independent node in the AST.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class StringList extends Symbol implements Iterable<String> {
    private ArrayList<String> Strings;

    /**
     * Create an empty String list.
     */
    public StringList() {
        Strings = new ArrayList<>();
    }

    /**
     * Create a String list with a single String.
     *
     * @param s	the String that will be placed in the list
     */
    public StringList(String s) {
        this();
        Strings.add(s);
    }

    /**
     * Create a String list with the given head and tail.
     *
     * @param s  the head of the new String list
     * @param ss the tail of the new String list
     */
    public StringList(String s, StringList ss) {
        this();
        Strings.add(s);
        Strings.addAll(ss.Strings);
    }

    /**
     * Return an iterator over the Strings in the list.
     */
    @Override
    public Iterator<String> iterator() {
        return Strings.iterator();
    }
}
