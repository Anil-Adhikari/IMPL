package edu.ecu.cs.seng6245.imp.value;

import edu.ecu.cs.seng6245.imp.exceptions.TypeException;

public class BooleanValue extends ImpElement {
    private final Boolean boolValue;
    private final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    /**
     * Create a new BooleanValue wrapping the given Boolean.
     *
     * @param bv the Boolean represented by this BooleanValue
     */
    protected BooleanValue(Boolean bv) {
        boolValue = bv;
    }


    @Override
    public String type() {
        return "Boolean";
    }

    /**
     * Return the Boolean value stored inside this object.
     *
     * @return the Boolean value stored inside this object.
     */
    protected Boolean geBoolValue() {
        return boolValue;
    }

    @Override
    public boolean isTrue() {
        return boolValue;
    }

    @Override
    public boolean isFalse() {
        return !boolValue;
    }

	/* ******************************************************************************* */
	/* The first group of methods: these simply dispatch to the correct second method. */
	/* Note that we only need to define the methods that represent valid operators for */
	/* this type; if we don't implement this operator, we can just ignore it and let   */
	/* the definition in the parent class handle it.                                   */
	/* ******************************************************************************* */

    @Override
    public ImpValue plus(ImpValue iv) {
        return iv.plusBool(this);
    }

    @Override
    public ImpValue equal(ImpValue iv) {
        return iv.equalBool(this);
    }

    @Override
    public ImpValue notEqual(ImpValue iv) {
        return iv.notEqualBool(this);
    }

    @Override
    public ImpValue in(ImpValue iv) {
        return iv.inBool(this);
    }

    @Override
    public ImpValue notIn(ImpValue iv) {
        return iv.inBool(this).isFalse() ? vf.makeTrue() : vf.makeFalse();
    }

	/* ******************************************************************************* */
	/* The second group of methods: these actually implement the functionality needed  */
	/* for this value, for instance, the version of plus that works for two integers,  */
	/* represented here as the receiver (i.e., "this") and an argument of the proper   */
	/* type for an integer addition, IntegerValue. This is called "plusInt" to enable  */
	/* it to be distinguished from other types of plus, like adding two sets.          */
	/* ******************************************************************************* */

    @Override
    protected ImpValue plusStr(StringValue sv) {
        return vf.makeStr(sv.getStrValue() + this.boolValue.toString());
    }

    @Override
    protected ImpValue plusSet(SetValue iv) {
        return vf.makeSet(iv.getSetValue().insert(this));
    }

    @Override
    protected ImpValue plusList(ListValue iv) {
        return vf.makeList(iv.getListValue().insertAtEnd(this));
    }

    @Override
    protected ImpValue minusSet(SetValue iv) {
        return vf.makeSet(iv.getSetValue().remove(this));
    }

    @Override
    protected ImpValue minusList(ListValue iv) {
        return vf.makeList(iv.getListValue().remove(this));
    }

    @Override
    protected ImpValue equalBool(BooleanValue bv) {
        if (bv.boolValue.equals(this.boolValue)) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue notEqualBool(BooleanValue bv) {
        if (!bv.boolValue.equals(this.boolValue)) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((boolValue == null) ? 0 : boolValue.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BooleanValue other = (BooleanValue) obj;
        if (boolValue == null) {
            if (other.boolValue != null)
                return false;
        } else if (!boolValue.equals(other.boolValue))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.boolValue.toString();
    }

    @Override
    public int compareTo(ImpElement ie) {
        if (ie instanceof BooleanValue) {
            BooleanValue bv = (BooleanValue)ie;
            return boolValue.compareTo(bv.boolValue);
        } else {
            throw new TypeException();
        }
    }
}
