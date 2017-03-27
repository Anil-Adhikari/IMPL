package edu.ecu.cs.seng6245.imp.value;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.exceptions.InvalidOperationException;
import edu.ecu.cs.seng6245.imp.exceptions.TypeException;
import edu.ecu.cs.seng6245.values.ISet;
import edu.ecu.cs.seng6245.values.impl.ValueFactory;

public class SetValue extends ImpValue {

    private final ISet<ImpElement> iSet;
    private final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    private <T> void checkType(Class<T> expectedType) {
        if (iSet.size() == 0) {
            return;
        }
        if (iSet.getOneFrom().getClass().equals(expectedType)) {
            return;
        }
        throw new TypeException("Cannot alter type of populated set");
    }

    protected SetValue(ISet<ImpElement> set) {
        iSet = set;
    }

    @Override
    public String type() {
        return "Set";
    }

    /**
     * Return the set value stored inside this object.
     *
     * @return the set value stored inside this object.
     */
    protected ISet<ImpElement> getSetValue() {
        return iSet;
    }

	/* ******************************************************************************* */
	/* The first group of methods: these simply dispatch to the correct second method. */
	/* Note that we only need to define the methods that represent valid operators for */
	/* this type; if we don't implement this operator, we can just ignore it and let   */
	/* the definition in the parent class handle it.                                   */
	/* ******************************************************************************* */

    @Override
    public ImpValue plus(ImpValue iv) {
        return iv.plusSet(this);
    }

    @Override
    public ImpValue minus(ImpValue iv) {
        return iv.minusSet(this);
    }

    @Override
    public ImpValue equal(ImpValue iv) {
        return iv.equalSet(this);
    }

    @Override
    public ImpValue notEqual(ImpValue iv) {
        return iv.notEqualSet(this);
    }

    @Override
    public ImpValue size() {
        return vf.makeInt(iSet.size());
    }

    @Override
    public ImpValue choose() {
        return vf.makeGeneric(iSet.getOneFrom());
    }

    @Override
    public ImpValue union(ImpValue iv) {
        return iv.unionSet(this);
    }

    @Override
    public ImpValue intersection(ImpValue iv) {
        return iv.intersectSet(this);
    }

    @Override
    public ImpValue subsetOf(ImpValue iv) {
        return iv.subsetOfSet(this);
    }

    @Override
    public Iterator<ImpValue> getNamedIterator(String s) {
        switch (s) {
            case "standard":
                return new SetIterator(iSet.getStandardIterator());
            case "sorted":
                return new SetIterator(iSet.getSortedIterator());
            case "even":
                ISet<Integer> forIteration = ValueFactory.getValueFactory().makeIntegerSet();
                Iterator<ImpElement> iter = iSet.getStandardIterator();
                while (iter.hasNext()) {
                    ImpElement nextElement = iter.next();
                    if (nextElement instanceof IntegerValue) {
                        forIteration = forIteration.insert(((IntegerValue) nextElement).getIntValue());
                    }
                }
                return new SetIterator(forIteration.getEvenIterator());
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
        return vf.makeSet(iSet.insert(iv));
    }

    @Override
    protected ImpValue plusStr(StringValue sv) {
        checkType(StringValue.class);
        return vf.makeSet(iSet.insert(sv));
    }

    @Override
    protected ImpValue plusBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return vf.makeSet(iSet.insert(bv));
    }

    @Override
    protected ImpValue equalSet(SetValue iv) {
        return iv.iSet.equals(this.iSet) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue notEqualSet(SetValue iv) {
        return iv.iSet.equals(this.iSet) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    protected ImpValue inInt(IntegerValue iv) {
        checkType(IntegerValue.class);
        return iSet.in(iv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue inStr(StringValue sv) {
        checkType(StringValue.class);
        return iSet.in(sv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue inBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return iSet.in(bv) ? vf.makeTrue() : vf.makeFalse();
    }

    @Override
    protected ImpValue notInInt(IntegerValue iv) {
        checkType(IntegerValue.class);
        return iSet.in(iv) ? vf.makeFalse() : vf.makeTrue();
    }

    @Override
    protected ImpValue notInStr(StringValue sv) {
        checkType(StringValue.class);
        return iSet.in(sv) ? vf.makeFalse() : vf.makeTrue() ;
    }

    @Override
    protected ImpValue notInBool(BooleanValue bv) {
        checkType(BooleanValue.class);
        return iSet.in(bv) ? vf.makeFalse() : vf.makeTrue() ;
    }

    @Override
    public ImpValue unionSet(SetValue iv) {
        return vf.makeSet(iv.getSetValue().union(iSet));
    }

    @Override
    public ImpValue intersectSet(SetValue iv) {
        return vf.makeSet(iv.getSetValue().intersection(iSet));
    }

    @Override
    public ImpValue subsetOfSet(SetValue iv) {
        return vf.makeBool(iv.getSetValue().subsetOf(iSet));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((iSet == null) ? 0 : iSet.hashCode());
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
        SetValue other = (SetValue) obj;
        if (iSet == null) {
            if (other.iSet != null)
                return false;
        } else if (!iSet.equals(other.iSet))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return iSet.toString();
    }

    /**
     * This class wraps the set iterators to turn their values into ImpValues instead
     * of regular Integers.
     *
     * @author Mark Hills
     * @version 1.0
     *
     */
    private static class SetIterator implements Iterator<ImpValue> {
        private Iterator<?> internalIterator;

        public SetIterator(Iterator<?> wrappedIterator) {
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