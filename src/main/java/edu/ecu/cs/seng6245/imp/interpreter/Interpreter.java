package edu.ecu.cs.seng6245.imp.interpreter;

import edu.ecu.cs.seng6245.imp.ast.Statement;
import edu.ecu.cs.seng6245.imp.parser.ParserUtil;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * The interpreter for the IMP language.
 * 
 * @author Mark Hills
 * @version 1.0
 *
 */
public class Interpreter {

    /** 
     * The environment used by the interpreter when one is not explicitly given,
     * this is persistent between calls unless the interpreter is reset
     */
    private final Environment env;
    
    /**
     * Create a new instance of the interpreter.
     */
    public Interpreter() {
        env = new Environment();
    }
    
    /**
     * Interprets the program cmd in the current environment.
     * 
     * @param cmd the command to interpret
     * 
     * @return the value returned by the command
     */
    public ImpValue interpret(String cmd) {
        return interpret(cmd, this.env);
    }

    /**
     * Interprets the program cmd in the given environment.
     * 
     * @param cmd the command to interpret
     * @param env the environment in which to interpret the command
     * 
     * @return the value returned by the command
     */
    public ImpValue interpret(String cmd, Environment env) {
        Statement stmt = ParserUtil.parseStatement(cmd);
        
        if (stmt != null) {
            ImpValue iv = stmt.interpret(env);
            return iv;
        }
        return ImpValueFactory.getValueFactory().makeVoid();
    }

    /**
     * Resets the interpreter back to its starting state.
     */
    public void resetInterpreter() {
        env.clear();
    }
    
    /**
     * Retrieve the current environment.
     * 
     * @return the current environment
     */
    public Environment getCurrentEnvironment() {
        return env;
    }
}
