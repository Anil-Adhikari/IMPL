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
        return new IntegerSet();
    }

    /**
     * Create a new integer list with the given elements
     */
    public IList<Integer> makeIntegerList() {
        return new IntegerList();
    }

    /**
     * Create a new empty string set
     */
    public ISet<String> makeStringSet() {
        // TODO: What should actually be returned here? This should not return null!
		return null;
    }

    /**
     * Create a new empty string list
     */
    public IList<String> makeStringList() {
		// TODO: What should actually be returned here? This should not return null!
		return null;
    }

    /**
     * Create a new empty boolean set
     */
    public ISet<Boolean> makeBooleanSet() {
		// TODO: What should actually be returned here? This should not return null!
		return null;
    }

    /**
     * Create a new empty boolean list
     */
    public IList<Boolean> makeBooleanList() {
		// TODO: What should actually be returned here? This should not return null!
		return null;
    }

    /**
     * Create a new ImpElement set
     */
    public ISet<ImpElement> makeImpElementSet() {
		// TODO: What should actually be returned here? This should not return null!
		return null;
    }

    /**
     * Create a new ImpElement list
     */
    public IList<ImpElement> makeImpElementList() {
		// TODO: What should actually be returned here? This should not return null!
		return null;
    }
}
