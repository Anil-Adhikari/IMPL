package edu.ecu.cs.seng6245.imp.value;

import java.util.List;

import edu.ecu.cs.seng6245.values.IList;
import edu.ecu.cs.seng6245.values.ISet;
import edu.ecu.cs.seng6245.values.impl.ValueFactory;

/**
 * This class is a factory which knows how to create IMP values.
 *
 * @author Mark Hills
 * @version 1.2
 *
 */
public class ImpValueFactory {
    /** The value factory is a singleton, this holds the instance */
    private final static ImpValueFactory vf = new ImpValueFactory();

    /** The void value is a singleton, this holds the instance */
    private final static VoidValue voidValue = new VoidValue();

    /** The singleton for true */
    private final static BooleanValue trueValue = new BooleanValue(true);

    /** The singleton for false */
    private final static BooleanValue falseValue = new BooleanValue(false);

    /**
     * Return the instance of the value factory.
     *
     * @return the value factory
     */
    public static ImpValueFactory getValueFactory() {
        return vf;
    }

    /**
     * Make an IntegerValue given the integer held inside.
     *
     * @param n the integer to create an IntegerValue for
     *
     * @return the new IntegerValue
     */
    public IntegerValue makeInt(Integer n) {
        return new IntegerValue(n);
    }

    /**
     * Make an IntegerValue representing True.
     *
     * @return an IntegerValue representing True
     */
    public BooleanValue makeTrue() {
        return trueValue;
    }

    /**
     * Make an IntegerValue representing False.
     *
     * @return the IntegerValue representing False
     */
    public BooleanValue makeFalse() {
        return falseValue;
    }

    /**
     * Make a VoidValue
     *
     * @return VoidValue
     */
    public VoidValue makeVoid() {
        return voidValue;
    }

    /**
     * Make a SetValue representing the empty set
     *
     * @return the empty set
     */
    public SetValue makeSet() {
        return new SetValue(ValueFactory.getValueFactory().makeImpElementSet());
    }

    /**
     * Make a SetValue with the same elements as the given set
     *
     * @param ns an existing set
     *
     * @return a new set with the same elements
     */
    public SetValue makeSet(ISet<ImpElement> ns) {
        return new SetValue(ns);
    }

    /**
     * Make an SetValue with the given integers
     *
     * @param nl a list of integers
     *
     * @return a new set with the given elements
     */
    public SetValue makeIntegerSet(List<Integer> nl) {
        ISet<ImpElement> iset = ValueFactory.getValueFactory().makeImpElementSet();
        for (Integer n : nl) iset = iset.insert(vf.makeInt(n));
        return new SetValue(iset);
    }

    /**
     * Make an SetValue with the given booleans
     *
     * @param bl a list of booleans
     *
     * @return a new set with the given elements
     */
    public SetValue makeBooleanSet(List<Boolean> bl) {
        ISet<ImpElement> iset = ValueFactory.getValueFactory().makeImpElementSet();
        for (Boolean b : bl) iset = iset.insert(vf.makeBool(b));
        return new SetValue(iset);
    }

    /**
     * Make an SetValue with the given strings
     *
     * @param sl a list of strings
     *
     * @return a new set with the given elements
     */
    public SetValue makeStringSet(List<String> sl) {
        ISet<ImpElement> iset = ValueFactory.getValueFactory().makeImpElementSet();
        for (String s : sl) iset = iset.insert(vf.makeStr(s));
        return new SetValue(iset);
    }

    /**
     * Make an ListValue representing the empty list
     *
     * @return the empty integer list
     */
    public ListValue makeList() {
        return new ListValue(ValueFactory.getValueFactory().makeImpElementList());
    }

    /**
     * Make a ListValue with the same elements as the given list
     *
     * @param ns an existing list
     *
     * @return a new list with the same elements
     */
    public ListValue makeList(IList<ImpElement> ns) {
        return new ListValue(ns);
    }

    /**
     * Make a ListValue with the given integers
     *
     * @param nl a list of integers
     *
     * @return a new integer list with the given elements
     */
    public ListValue makeIntegerList(List<Integer> nl) {
        IList<ImpElement> ilist = ValueFactory.getValueFactory().makeImpElementList();
        for (Integer n : nl) ilist = ilist.insertAtEnd(vf.makeInt(n));
        return new ListValue(ilist);
    }

    /**
     * Make a ListValue with the given strings
     *
     * @param sl a list of strings
     *
     * @return a new string list with the given elements
     */
    public ListValue makeStringList(List<String> sl) {
        IList<ImpElement> ilist = ValueFactory.getValueFactory().makeImpElementList();
        for (String s : sl) ilist = ilist.insertAtEnd(vf.makeStr(s));
        return new ListValue(ilist);
    }

    /**
     * Make a ListValue with the given booleans
     *
     * @param bl a list of booleans
     *
     * @return a new boolean list with the given elements
     */
    public ListValue makeBooleanList(List<Boolean> bl) {
        IList<ImpElement> ilist = ValueFactory.getValueFactory().makeImpElementList();
        for (Boolean b : bl) ilist = ilist.insertAtEnd(vf.makeBool(b));
        return new ListValue(ilist);
    }

    /**
     * Make a string value given the string contained inside.
     *
     * @param string the string value contained inside
     *
     * @return a new string value
     */
    public StringValue makeStr(String string) {
        return new StringValue(string);
    }

    /**
     * Make a boolean value given the boolean contained inside.
     *
     * @param bool the boolean value contained inside
     *
     * @return a new boolean value
     */
    public BooleanValue makeBool(Boolean bool) {
        return new BooleanValue(bool);
    }

    /**
     * Make a value of the appropriate type, based on the runtime type of the parameter.
     *
     * @param o the value to encapsulate as an {@link ImpValue}
     *
     * @return a new {@link ImpValue} holding the input object, or void if an unrecognized
     *         type was passed
     */
    public ImpValue makeGeneric(Object o) {
        if (o instanceof Integer) {
            return makeInt((Integer)o);
        } else if (o instanceof String) {
            return makeStr((String)o);
        } else if (o instanceof Boolean) {
            return makeBool((Boolean)o);
        }  else if (o instanceof ImpElement) {
            return (ImpElement)o;
        }

        // We may want to throw an exception here, but this will make IMP harder to crash
        // during the homework at least.
        System.err.println("WARNING: Failed to identify proper make method for parameter " + o.toString());
        return makeVoid();
    }
}