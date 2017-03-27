package edu.ecu.cs.seng6245.imp.value;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;

public abstract class ImpValue {

    /**
     * The type of the value as a string.
     *
     * @return the string representation of the type.
     */
    public abstract String type();

    /**
     * Indicate if this value represents boolean true.
     *
     * @return true when this value represents boolean true, false otherwise.
     */
    public boolean isTrue() {
        throw new InvalidOperationException("isTrue", this);
    }

    /**
     * Indicate if this value represents boolean false.
     *
     * @return true when this value represents boolean false, false otherwise.
     */
    public boolean isFalse() {
        throw new InvalidOperationException("isFalse", this);
    }

	/* ******************************************************************************* */
	/* The first group of methods: these simply dispatch to the correct second method. */
	/* Note that we only need to define the methods that represent valid operators for */
	/* this type; if we don't implement this operator, we can just ignore it and let   */
	/* the definition in the parent class handle it.                                   */
	/* ******************************************************************************* */

    public ImpValue plus(ImpValue iv) {
        throw new InvalidOperationException("plus", this, iv);
    }

    public ImpValue minus(ImpValue iv) {
        throw new InvalidOperationException("minus", this, iv);
    }

    public ImpValue times(ImpValue iv) {
        throw new InvalidOperationException("times", this, iv);
    }

    public ImpValue div(ImpValue iv) {
        throw new InvalidOperationException("div", this, iv);
    }

    public ImpValue equal(ImpValue iv) {
        throw new InvalidOperationException("equal", this, iv);
    }

    public ImpValue notEqual(ImpValue iv) {
        throw new InvalidOperationException("notEqual", this, iv);
    }

    public ImpValue lessThan(ImpValue iv) {
        throw new InvalidOperationException("lessThan", this, iv);
    }

    public ImpValue lessThanOrEqual(ImpValue iv) {
        throw new InvalidOperationException("lessThanOrEqual", this, iv);
    }

    public ImpValue greaterThan(ImpValue iv) {
        throw new InvalidOperationException("greaterThan", this, iv);
    }

    public ImpValue greaterThanOrEqual(ImpValue iv) {
        throw new InvalidOperationException("greaterThanOrEqual", this, iv);
    }

    public ImpValue in(ImpValue iv) {
        throw new InvalidOperationException("in", this, iv);
    }

    public ImpValue notIn(ImpValue iv) {
        throw new InvalidOperationException("notIn", this, iv);
    }

    public ImpValue size() {
        throw new InvalidOperationException("size", this);
    }

    public ImpValue choose() {
        throw new InvalidOperationException("choose", this);
    }

    public ImpValue get(ImpValue iv) {
        throw new InvalidOperationException("get", this, iv);
    }

    public Iterator<ImpValue> getNamedIterator(String iteratorName) {
        throw new InvalidOperationException("getNamedIterator", this);
    }

    public ImpValue head() {
        throw new InvalidOperationException("head", this);
    }

    public ImpValue tail() {
        throw new InvalidOperationException("tail", this);
    }

    public ImpValue union(ImpValue iv) {
        throw new InvalidOperationException("union", this, iv);
    }

    public ImpValue intersection(ImpValue iv) {
        throw new InvalidOperationException("intersection", this, iv);
    }

    public ImpValue subsetOf(ImpValue iv) {
        throw new InvalidOperationException("subsetOf", this, iv);
    }

	/* ******************************************************************************* */
	/* The second group of methods: these actually implement the functionality needed  */
	/* for the value. These need to be overridden in any class that supports the       */
	/* operation: so, any class that allows expressions of the form "int + this",      */
	/* where "this" is the class, should implement plusInt. Note that, because of      */
	/* double dispatch, these are all "flipped", so something like 5+3 becomes a call  */
	/* that looks like IntegerValue(3).plusInt(IntegerValue(5)).                       */
	/* ******************************************************************************* */

    protected ImpValue plusInt(IntegerValue iv) {
        throw new InvalidOperationException("plusInt", iv, this);
    }

    protected ImpValue plusStr(StringValue sv) {
        throw new InvalidOperationException("plusStr", sv, this);
    }

    protected ImpValue plusBool(BooleanValue bv) {
        throw new InvalidOperationException("plusBool", bv, this);
    }

    protected ImpValue plusSet(SetValue iv) {
        throw new InvalidOperationException("plusSet", iv, this);
    }

    protected ImpValue plusList(ListValue iv) {
        throw new InvalidOperationException("plusList", iv, this);
    }

    protected ImpValue minusInt(IntegerValue iv) {
        throw new InvalidOperationException("minusInt", iv, this);
    }

    protected ImpValue minusSet(SetValue iv) {
        throw new InvalidOperationException("minusSet", iv, this);
    }

    protected ImpValue minusList(ListValue iv) {
        throw new InvalidOperationException("minusList", iv, this);
    }

    protected ImpValue timesInt(IntegerValue iv) {
        throw new InvalidOperationException("timesInt", iv, this);
    }

    protected ImpValue divInt(IntegerValue iv) {
        throw new InvalidOperationException("divInt", iv, this);
    }

    protected ImpValue equalInt(IntegerValue iv) {
        throw new InvalidOperationException("equalInt", iv, this);
    }

    protected ImpValue equalStr(StringValue sv) {
        throw new InvalidOperationException("equalStr", sv, this);
    }

    protected ImpValue equalBool(BooleanValue bv) {
        throw new InvalidOperationException("equalBool", bv, this);
    }

    protected ImpValue equalSet(SetValue iv) {
        throw new InvalidOperationException("equalSet", iv, this);
    }

    protected ImpValue equalList(ListValue iv) {
        throw new InvalidOperationException("equalList", iv, this);
    }

    protected ImpValue notEqualInt(IntegerValue iv) {
        throw new InvalidOperationException("notEqualInt", iv, this);
    }

    protected ImpValue notEqualStr(StringValue sv) {
        throw new InvalidOperationException("notEqualStr", sv, this);
    }

    protected ImpValue notEqualBool(BooleanValue bv) {
        throw new InvalidOperationException("notEqualBool", bv, this);
    }

    protected ImpValue notEqualSet(SetValue iv) {
        throw new InvalidOperationException("notEqualSet", iv, this);
    }

    protected ImpValue notEqualList(ListValue iv) {
        throw new InvalidOperationException("notEqualList", iv, this);
    }

    protected ImpValue lessThanInt(IntegerValue iv) {
        throw new InvalidOperationException("lessThanInt", iv, this);
    }

    protected ImpValue lessThanOrEqualInt(IntegerValue iv) {
        throw new InvalidOperationException("lessThanOrEqualInt", iv, this);
    }

    protected ImpValue greaterThanInt(IntegerValue iv) {
        throw new InvalidOperationException("greaterThanInt", iv, this);
    }

    protected ImpValue greaterThanOrEqualInt(IntegerValue iv) {
        throw new InvalidOperationException("greaterThanOrEqualInt", iv, this);
    }

    protected ImpValue inInt(IntegerValue iv) {
        throw new InvalidOperationException("in", iv, this);
    }

    protected ImpValue inStr(StringValue sv) {
        throw new InvalidOperationException("in", sv, this);
    }

    protected ImpValue inBool(BooleanValue bv) {
        throw new InvalidOperationException("in", bv, this);
    }

    protected ImpValue notInInt(IntegerValue iv) {
        throw new InvalidOperationException("notIn", iv, this);
    }

    protected ImpValue notInStr(StringValue sv) {
        throw new InvalidOperationException("notIn", sv, this);
    }

    protected ImpValue notInBool(BooleanValue bv) {
        throw new InvalidOperationException("notIn", bv, this);
    }

    protected ImpValue getList(ListValue iv) {
        throw new InvalidOperationException("get", iv, this);
    }

    protected ImpValue unionSet(SetValue iv) {
        throw new InvalidOperationException("union", this, iv);
    }

    protected ImpValue intersectSet(SetValue iv) {
        throw new InvalidOperationException("intersection", this, iv);
    }

    protected ImpValue subsetOfSet(SetValue iv) {
        throw new InvalidOperationException("subsetOf", this, iv);
    }
}
