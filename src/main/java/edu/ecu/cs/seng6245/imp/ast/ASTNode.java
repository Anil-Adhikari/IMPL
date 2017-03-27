package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * Abstract base class for all AST nodes in IMP.
 * 
 * @author Mark Hills
 * @version 1.0
 */
public abstract class ASTNode extends beaver.Symbol {
    /**
     * Interpret this construct, returning the resulting value.
     * 
     * @param env the current environment
     * 
     * @return the computed {@link ImpValue}
     */
    public abstract ImpValue interpret(Environment env);
}
