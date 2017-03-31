package edu.ecu.cs.seng6245.values.impl;

import edu.ecu.cs.seng6245.values.IList;
import edu.ecu.cs.seng6245.values.ISet;
import edu.ecu.cs.seng6245.imp.value.ImpElement;

public class ValueFactory {
    /** The value factory is a singleton, this holds the instance */
    private final static ValueFactory vf = new ValueFactory();

    /**
     * Return the instance of the value factory.
     *
     * @return the value factory
     */
    public static ValueFactory getValueFactory() {
        return vf;
    }

    /**
     * Create a new integer set with the given elements
     */
    public ISet<Integer> makeIntegerSet() {
        return new GenericSet<>();
    }

    /**
     * Create a new integer list with the given elements
     */
    public IList<Integer> makeIntegerList() {
        return new GenericList<>();
    }

    /**
     * Create a new empty string set
     */
    public ISet<String> makeStringSet() {
        // Returns new generic set with type parameter String
        return new GenericSet<>();
    }

    /**
     * Create a new empty string list
     */
    public IList<String> makeStringList() {
        // Returns new generic list with type parameter String
        return new GenericList<>();
    }

    /**
     * Create a new empty boolean set
     */
    public ISet<Boolean> makeBooleanSet() {
        // Returns new generic set with type parameter Boolean
        return new GenericSet<>();
    }

    /**
     * Create a new empty boolean list
     */
    public IList<Boolean> makeBooleanList() {
        // Returns new generic list with type parameter Boolean
        return new GenericList<>();
    }

    /**
     * Create a new ImpElement set
     */
    public ISet<ImpElement> makeImpElementSet() {
        // Returns new generic set with type parameter ImpElement
        return new GenericSet<>();
    }

    /**
     * Create a new ImpElement list
     */
    public IList<ImpElement> makeImpElementList() {
		// Returns new generic list with type parameter ImpElement
		return new GenericList<>();
    }
}
