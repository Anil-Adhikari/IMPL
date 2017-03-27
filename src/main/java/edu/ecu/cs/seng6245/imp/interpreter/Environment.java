package edu.ecu.cs.seng6245.imp.interpreter;

import java.util.HashMap;

import edu.ecu.cs.seng6245.imp.exceptions.NameNotDefinedException;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

/**
 * An environment, holding the mapping from names to values.
 * 
 * @author Mark Hills
 * @version 1.1
 */
public class Environment {

    private HashMap<String,ImpValue> env = new HashMap<>();
    
    /**
     * Set the value of the identifier id in the environment.
     * 
     * @param id the id to set
     * @param val the value to set the id to
     */
    public void setValue(String id, ImpValue val) {
        env.put(id, val);
    }
    
    /**
     * Look up the value assigned to id.
     * 
     * @param id the identifier to look up
     * @return the value assigned to id
     * @throws NameNotDefinedException when the id is not in the environment
     */
    public ImpValue getValue(String id) {
        if (env.containsKey(id)) {
            return env.get(id);
        }
        throw new NameNotDefinedException("Attempt to lookup undefined name", id);
    }
    
    /**
     * Remove all names from the environment
     */
    public void clear() {
        env.clear();
    }
}
