package edu.ecu.cs.seng6245.imp.value;

import edu.ecu.cs.seng6245.imp.exceptions.TypeException;

public class StringValue extends ImpElement {
    private final String strValue;
    private final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    /**
     * Create a new StringValue wrapping the given String.
     *
     * @param sv the String represented by this StringValue
     */
    protected StringValue(String sv) {
        strValue = sv;
    }

    @Override
    public String type() {
        return "String";
    }

    /**
     * Return the String value stored inside this object.
     *
     * @return the String value stored inside this object.
     */
    protected String getStrValue() {
        return strValue;
    }

    /* ******************************************************************************* */
    /* The first group of methods: these simply dispatch to the correct second method. */
    /* Note that we only need to define the methods that represent valid operators for */
    /* this type; if we don't implement this operator, we can just ignore it and let   */
    /* the definition in the parent class handle it.                                   */
    /* ******************************************************************************* */

    @Override
    public ImpValue plus(ImpValue iv) {
        return iv.plusStr(this);
    }

    @Override
    public ImpValue equal(ImpValue iv) {
        return iv.equalStr(this);
    }

    @Override
    public ImpValue notEqual(ImpValue iv) {
        return iv.notEqualStr(this);
    }

    @Override
    public ImpValue in(ImpValue iv) {
        return iv.inStr(this);
    }

    @Override
    public ImpValue notIn(ImpValue iv) {
        return iv.inStr(this).isFalse() ? vf.makeTrue() : vf.makeFalse();
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
        return vf.makeStr(sv.strValue + this.strValue);
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
    protected ImpValue equalStr(StringValue sv) {
        if (sv.strValue.equals(this.strValue)) {
            return vf.makeTrue();
        } else {
            return vf.makeFalse();
        }
    }

    @Override
    protected ImpValue notEqualStr(StringValue sv) {
        if (sv.strValue.equals(this.strValue)) {
            return vf.makeFalse();
        } else {
            return vf.makeTrue();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((strValue == null) ? 0 : strValue.hashCode());
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
        StringValue other = (StringValue) obj;
        if (strValue == null) {
            if (other.strValue != null)
                return false;
        } else if (!strValue.equals(other.strValue))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "\"" +  strValue + "\"";
    }

    @Override
    public int compareTo(ImpElement ie) {
        if (ie instanceof StringValue) {
            StringValue sv = (StringValue)ie;
            return strValue.compareTo(sv.strValue);
        } else {
            throw new TypeException();
        }
    }
}
