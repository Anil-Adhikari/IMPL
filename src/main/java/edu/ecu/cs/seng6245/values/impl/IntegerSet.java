package edu.ecu.cs.seng6245.values.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import edu.ecu.cs.seng6245.imp.exceptions.EmptySetException;
import edu.ecu.cs.seng6245.values.ISet;

class IntegerSet implements ISet<Integer> {
    /** The internal representation of the integer set */
    private final HashSet<Integer> intSet;

    /**
     * Create a new, empty set of integers
     *
     */
    public IntegerSet() {
        intSet = new HashSet<>();
    }

    /**
     * Create a new, non-empty integer set using the given HashSet.
     *
     * @param hs The set backing our integer set implementation.
     */
    protected IntegerSet(HashSet<Integer> hs) {
        if (hs == null) throw new NullPointerException("Initializing Hash Set cannot be null");
        intSet = hs;
    }

    @Override
    public ISet<Integer> insert(Integer i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");

        if (intSet.contains(i)) {
            return this;
        } else {
            HashSet<Integer> hs = new HashSet<>(intSet);
            hs.add(i);
            return new IntegerSet(hs);
        }
    }

    @Override
    public ISet<Integer> remove(Integer i) {
        if (i == null) throw new NullPointerException("Element to remove cannot be null");

        if (!intSet.contains(i)) {
            return this;
        } else {
            HashSet<Integer> hs = new HashSet<>(intSet);
            hs.remove(i);
            return new IntegerSet(hs);
        }
    }

    @Override
    public boolean in(Integer i) {
        if (i == null) throw new NullPointerException("Element to check for membership cannot be null");
        return intSet.contains(i);
    }

    @Override
    public Integer size() {
        return intSet.size();
    }

    @Override
    public Integer getOneFrom() {
        if (intSet.isEmpty()) {
            throw new EmptySetException("getOneFrom cannot be called on an empty set");
        }
        List<Integer> lst = new ArrayList<>(intSet);
        Collections.shuffle(lst);
        return lst.get(0);
    }

    @Override
    public ISet<Integer> union(ISet<Integer> set) {
        // TODO Add the code for set union here.
        return null;
    }

    @Override
    public ISet<Integer> intersection(ISet<Integer> set) {
        // TODO Add the code for set intersection here.
        return null;
    }

    @Override
    public boolean subsetOf(ISet<Integer> set) {
        // TODO Add the code for subset of here.
        return false;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int hashCode() {
        // Note: This hashCode method was auto-generated. We aren't using
        // it, but it doesn't hurt to have it so I just left it here.
        final int prime = 31;
        int result = 1;
        result = prime * result + ((intSet == null) ? 0 : intSet.hashCode());
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean equals(Object obj) {
        // NOTE: This equals method was auto-generated, which works fine
        // in this case.
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntegerSet other = (IntegerSet) obj;
        if (intSet == null) {
            if (other.intSet != null)
                return false;
        } else if (!intSet.equals(other.intSet))
            return false;
        return true;
    }

    @Override
    public String toString() {
        // NOTE: This assumes that toArray is run once, since we have no guarantee
        // on the order in which elements are returned.
        // AF(c) = { c.intSet.toArray[i].intValue | 0 <= i < c.intSet.size }

        StringBuilder buf = new StringBuilder();
        buf.append("{");
        boolean firstItem = true;
        for (Integer i : intSet) {
            if (!firstItem) {
                buf.append(", ");
            } else {
                firstItem = false;
            }
            buf.append(i);
        }
        buf.append("}");
        return buf.toString();
    }

    /**
     * Check the representation to make sure the invariant holds.
     *
     * @return true when the invariant holds, false otherwise
     */
    @SuppressWarnings("ConstantConditions")
    public boolean repOk() {
        // RI = The intSet is not null and no elements of intSet are null and there are
        //      no duplicates in the intSet and intSet.size > 1

        // NOTE: This is established by the constructor, but we can still check it here.
        if (intSet == null) {
            return false;
        }

        for (Integer num : intSet) {
            if (num == null) {
                return false;
            }
        }

        // NOTE: The HashSet already takes care of duplicates, but if we wanted to check
        // for them anyway, we would want to do something like the following
        HashSet<Integer> seenBefore = new HashSet<>();
        for (Integer num : intSet) {
            if (seenBefore.contains(num)) {
                return false;
            }
            seenBefore.add(num);
        }

        return true;
    }

    @Override
    public Iterator<Integer> getStandardIterator() {
        return new StandardSetIterator(intSet);
    }

    @Override
    public Iterator<Integer> getSortedIterator() {
        return new SortedSetIterator(intSet);
    }

    @Override
    public Iterator<Integer> getEvenIterator() {
        return new EvenSetIterator(intSet);
    }

    private static class StandardSetIterator implements Iterator<Integer> {
        private final List<Integer> localSetAsList;
        private int currentIndex;

        public StandardSetIterator(Set<Integer> intSet) {
            // We turn this into a list so we can use standard indexing operations
            // to work our way through; we could also use the normal iterator returned
            // by the set itself, in which case we would have an instance variable
            // holding the iterator that we could then use below.
            localSetAsList = new ArrayList<>(intSet);
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localSetAsList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex < localSetAsList.size()) {
                return localSetAsList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the standard set iterator: StandardSetIterator.next");
            }
        }
    }

    private static class SortedSetIterator implements Iterator<Integer> {
        private final List<Integer> localSetAsList;
        private int currentIndex;

        public SortedSetIterator(Set<Integer> intSet) {
            List<Integer> l = new ArrayList<>(intSet);
            Collections.sort(l);
            localSetAsList = l;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localSetAsList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex < localSetAsList.size()) {
                return localSetAsList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the sorted set iterator: SortedSetIterator.next");
            }
        }
    }

    private static class EvenSetIterator implements Iterator<Integer> {
        private final List<Integer> localSetAsList;
        private int currentIndex;

        public EvenSetIterator(Set<Integer> intSet) {
            localSetAsList = new LinkedList<>(intSet);
            currentIndex = 0;
            while (currentIndex < localSetAsList.size() && localSetAsList.get(currentIndex) % 2 == 1) {
                ++currentIndex;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localSetAsList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex >= localSetAsList.size()) {
                throw new NoSuchElementException("No next element in the even set iterator: EvenSetIterator.next");
            }
            Integer res = localSetAsList.get(currentIndex++);
            while (currentIndex < localSetAsList.size() && localSetAsList.get(currentIndex) % 2 == 1) {
                ++currentIndex;
            }
            return res;
        }
    }
}
