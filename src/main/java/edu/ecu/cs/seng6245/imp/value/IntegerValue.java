package edu.ecu.cs.seng6245.imp.value;

import edu.ecu.cs.seng6245.imp.exceptions.TypeException;

public class IntegerValue extends ImpElement {
    private final Integer intValue;
    private final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    /**
     * Create a new IntegerValue wrapping the given integer.
     *
     * @param iv the integer represented by this IntegerValue
     */
    protected IntegerValue(Integer iv) {
        intValue = iv;
    }

    @Override
    public String type() {
        return "Integer";
    }

    /**
     * Return the integer value stored inside this object.
     *
     * @return the integer value stored inside this object.
     */
    protected Integer getIntValue() {
        return intValue;
    }

	/* ******************************************************************************* */
	/* The first group of methods: these simply dispatch to the correct second method. */
	/* Note that we only need to define the methods that represent valid operators for */
	/* this type; if we don't implement this operator, we can just ignore it and let   */
	/* the definition in the parent class handle it.                                   */
	/* ******************************************************************************* */

    @Override
    public ImpValue plus(ImpValue iv) {
        return iv.plusInt(this);
    }

    @Override
    public ImpValue minus(ImpValue iv) {
        return iv.minusInt(this);
    }

    @Override
    public ImpValue times(ImpValue iv) {
        return iv.timesInt(this);
    }

    @Override
    public ImpValue div(ImpValue iv) {
        return iv.divInt(this);
    }

    @Override
    public ImpValue equal(ImpValue iv) {
        return iv.equalInt(this);
    }

    @Override
    public ImpValue notEqual(ImpValue iv) {
        return iv.notEqualInt(this);
    }

    @Override
    public ImpValue lessThan(ImpValue iv) {
        return iv.lessThanInt(this);
    }

    @Override
    public ImpValue lessThanOrEqual(ImpValue iv) {
        return iv.lessThanOrEqualInt(this);
    }

    @Override
    public ImpValue greaterThan(ImpValue iv) {
        return iv.greaterThanInt(this);
    }

    @Override
    public ImpValue greaterThanOrEqual(ImpValue iv) {
        return iv.greaterThanOrEqualInt(this);
    }

    @Override
    public ImpValue in(ImpValue iv) {
        return iv.inInt(this);
    }

    @Override
    public ImpValue notIn(ImpValue iv) {
        return iv.inInt(this).isFalse() ? vf.makeTrue() : vf.makeFalse();
    }

	/* ******************************************************************************* */
	/* The second group of methods: these actually implement the functionality needed  */
	/* for this value, for instance, the version of plus that works for two integers,  */
	/* represented here as the receiver (i.e., "this") and an argument of the proper   */
	/* type for an integer addition, IntegerValue. This is called "plusInt" to enable  */
	/* it to be distinguished from other types of plus, like adding two sets.          */
	/* ******************************************************************************* */

    @Override
    protected ImpValue plusInt(IntegerValue iv) {
        return vf.makeInt(this.intValue + iv.intValue);
    }

    @Override
    protected ImpValue plusStr(StringValue sv) {
        return vf.makeStr(sv.getStrValue() + this.intValue.toString());
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
    protected ImpValue minusInt(IntegerValue iv) {
        return vf.makeInt(iv.intValue - this.intValue);
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
    protected ImpValue timesInt(IntegerValue iv) {
        return vf.makeInt(this.intValue * iv.intValue);
    }

    @Override
    protected ImpValue divInt(IntegerValue iv) {
        return vf.makeInt(iv.intValue / this.intValue);
    }

    @Override
    protected ImpValue equalInt(IntegerValue iv) {
        if (iv.intValue.equals(this.intValue)) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue notEqualInt(IntegerValue iv) {
        if (!iv.intValue.equals(this.intValue)) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue lessThanInt(IntegerValue iv) {
        if (iv.intValue < this.intValue) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue lessThanOrEqualInt(IntegerValue iv) {
        if (iv.intValue <= this.intValue) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue greaterThanInt(IntegerValue iv) {
        if (iv.intValue > this.intValue) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue greaterThanOrEqualInt(IntegerValue iv) {
        if (iv.intValue >= this.intValue) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue getList(ListValue iv) {
        return vf.makeGeneric(iv.getListValue().get(intValue));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((intValue == null) ? 0 : intValue.hashCode());
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
        IntegerValue other = (IntegerValue) obj;
        if (intValue == null) {
            if (other.intValue != null)
                return false;
        } else if (!intValue.equals(other.intValue))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return intValue.toString();
    }

    @Override
    public int compareTo(ImpElement ie) {
        if (ie instanceof IntegerValue) {
            IntegerValue iv = (IntegerValue)ie;
            return intValue.compareTo(iv.intValue);
        } else {
            throw new TypeException();
        }
    }
}
