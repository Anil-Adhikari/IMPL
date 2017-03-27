package edu.ecu.cs.seng6245.imp.value;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.exceptions.TypeException;
import edu.ecu.cs.seng6245.values.IList;

public class ListValue extends ImpValue {

    private final IList<ImpElement> iList;
    private final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    private <T> void checkType(Class<T> expectedType) {
        if (iList.size() == 0) {
            return;
        }
        if (iList.get(1).getClass().equals(expectedType)) {
            return;
        }
        throw new TypeException("Cannot alter type of populated list");
    }

    protected ListValue(IList<ImpElement> list) {
        iList = list;
    }

    @Override
    public String type() {
        return "List";
    }

    /**
     * Return the list value stored inside this object.
     *
     * @return the list value stored inside this object.
     */
    protected IList<ImpElement> getListValue() {
        return iList;
    }

	/* ******************************************************************************* */
	/* The first group of methods: these simply dispatch to the correct second method. */
	/* Note that we only need to define the methods that represent valid operators for */
	/* this type; if we don't implement this operator, we can just ignore it and let   */
	/* the definition in the parent class handle it.                                   */
	/* ******************************************************************************* */

    @Override
    public ImpValue plus(ImpValue iv) {
        return iv.plusList(this);
    }

    @Override
    public ImpValue minus(ImpValue iv) {
        return iv.minusList(this);
    }

    @Override
    public ImpValue equal(ImpValue iv) {
        return iv.equalList(this);
    }

    @Override
    public ImpValue notEqual(ImpValue iv) {
        return iv.notEqualList(this);
    }

    @Override
    public ImpValue size() {
        return vf.makeInt(iList.size());
    }

    @Override
    public ImpValue get(ImpValue iv) {
        return iv.getList(this);
    }

    @Override
    public ImpValue head() {
        return iList.head();
    }

    @Override
    public ImpValue tail() {
        return vf.makeList(iList.tail());
    }

    @Override
    public Iterator<ImpValue> getNamedIterator(String s) {
        switch (s) {
            case "standard":
                return new ListIterator(iList.getStandardIterator());
            case "sorted":
                return new ListIterator(iList.getSortedIterator());
            case "unique":
                return new ListIterator(iList.getUniqueIterator());
            default:
                throw new InvalidOperationException("getNamedIterator: " + s, this);
        }
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
        checkType(IntegerValue.class);
        return vf.makeList(iList.insertAtStart(iv));
    }

    @Override
    protected ImpValue plusStr(StringValue sv) {
        checkType(StringValue.class);
        return vf.makeList(iList.insertAtStart(sv));
    }

    @Override
    protected ImpValue plusBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return vf.makeList(iList.insertAtStart(bv));
    }

    @Override
    protected ImpValue equalList(ListValue iv) {
        return iv.iList.equals(this.iList) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue notEqualList(ListValue iv) {
        return iv.iList.equals(this.iList) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    protected ImpValue inInt(IntegerValue iv) {
        checkType(IntegerValue.class);
        return iList.in(iv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue inStr(StringValue sv) {
        checkType(StringValue.class);
        return iList.in(sv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue inBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return iList.in(bv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue notInInt(IntegerValue iv) {
        checkType(IntegerValue.class);
        return iList.in(iv) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    protected ImpValue notInStr(StringValue sv) {
        checkType(StringValue.class);
        return iList.in(sv) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    protected ImpValue notInBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return iList.in(bv) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((iList == null) ? 0 : iList.hashCode());
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
        ListValue other = (ListValue) obj;
        if (iList == null) {
            if (other.iList != null)
                return false;
        } else if (!iList.equals(other.iList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return iList.toString();
    }

    /**
     * This class wraps the list iterators to turn their values into ImpValues instead
     * of regular Integers.
     *
     * @author Mark Hills
     * @version 1.0
     *
     */
    private static class ListIterator implements Iterator<ImpValue> {
        private Iterator<?> internalIterator;

        public ListIterator(Iterator<?> wrappedIterator) {
            internalIterator = wrappedIterator;
        }

        @Override
        public boolean hasNext() {
            return internalIterator.hasNext();
        }

        @Override
        public ImpValue next() {
            return ImpValueFactory.getValueFactory().makeGeneric(internalIterator.next());
        }
    }
}
