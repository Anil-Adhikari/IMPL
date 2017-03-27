package edu.ecu.cs.seng6245.imp.ast;

import java.util.ArrayList;
import java.util.Iterator;

import beaver.Symbol;

/**
 * Represents a list of individual {@link Statement} items. Note
 * that we inherit {@link beaver.Symbol} and not {@link ASTNode}
 * since this isn't intended to be an independent node in the AST.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public class StatementList extends Symbol implements Iterable<Statement> {
    private ArrayList<Statement> statements;
    
    /**
     * Create an empty statement list.
     */
    public StatementList() {
        statements = new ArrayList<>();
    }
    
    /**
     * Create a statement list with a single statement.
     * 
     * @param s    the statement that will be placed in the list
     */
    public StatementList(Statement s) {
        this();
        statements.add(s);
    }
    
    /**
     * Create a statement list with the given head and tail.
     * 
     * @param s  the head of the new statement list
     * @param ss the tail of the new statement list
     */
    public StatementList(Statement s, StatementList ss) {
        this();
        statements.add(s);
        statements.addAll(ss.statements);
    }

    /**
     * Return an iterator over the statements in the list.
     */
    @Override
    public Iterator<Statement> iterator() {
        return statements.iterator();
    }
}
