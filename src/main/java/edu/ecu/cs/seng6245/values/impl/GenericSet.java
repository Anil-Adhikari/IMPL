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

class GenericSet<E extends Comparable<E>> implements ISet<E> {
    /** The internal representation of the integer set */
    private final HashSet<E> genericSet;

    /**
     * Create a new, empty set of integers
     *
     */
    GenericSet() {
        genericSet = new HashSet<>();
    }

    /**
     * Create a new, non-empty integer set using the given HashSet.
     *
     * @param hs The set backing our integer set implementation.
     */
    private GenericSet(HashSet<E> hs) {
        if (hs == null) throw new NullPointerException("Initializing Hash Set cannot be null");
        genericSet = hs;
    }

    @Override
    public ISet<E> insert(E i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");

        if (genericSet.contains(i)) {
            return this;
        } else {
            HashSet<E> hs = new HashSet<>(genericSet);
            hs.add(i);
            return new GenericSet<>(hs);
        }
    }

    @Override
    public ISet<E> remove(E i) {
        if (i == null) throw new NullPointerException("Element to remove cannot be null");

        if (!genericSet.contains(i)) {
            return this;
        } else {
            HashSet<E> hs = new HashSet<>(genericSet);
            hs.remove(i);
            return new GenericSet<>(hs);
        }
    }

    @Override
    public boolean in(E i) {
        if (i == null) throw new NullPointerException("Element to check for membership cannot be null");
        return genericSet.contains(i);
    }

    @Override
    public Integer size() {
        return genericSet.size();
    }

    @Override
    public E getOneFrom() {
        if (genericSet.isEmpty()) {
            throw new EmptySetException("getOneFrom cannot be called on an empty set");
        }
        List<E> lst = new ArrayList<>(genericSet);
        Collections.shuffle(lst);
        return lst.get(0);
    }

    @Override
    public ISet<E> union(ISet<E> set) {
        // Returns a new set with all the elements of two sets both common and uncommon element
        HashSet<E> pset = new HashSet<>();
        for(E elm : this.genericSet){
            pset.add(elm);
        }
        Iterator<E> itr = set.getStandardIterator();
        while(itr.hasNext()){
            pset.add(itr.next());
        }

        return new GenericSet<>(pset);
    }

    @Override
    public ISet<E> intersection(ISet<E> set) {
        // Returns a new set with only the common element among two sets
        HashSet<E> pset = new HashSet<>();
        for(E elm : this.genericSet){
            Iterator<E> itr = set.getStandardIterator();
            while(itr.hasNext()){
                if(elm.equals(itr.next())) pset.add(elm);
            }
        }
        return new GenericSet<>(pset);
    }

    @Override
    public boolean subsetOf(ISet<E> set) {
        // Returns true if this is subset of set
        int count = 0;
        for(E elm : this.genericSet){
            Iterator<E> itr = set.getStandardIterator();
            while(itr.hasNext()){
                if(elm.equals(itr.next())) count++;
            }
        }
        return count == this.genericSet.size();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int hashCode() {
        // Note: This hashCode method was auto-generated. We aren't using
        // it, but it doesn't hurt to have it so I just left it here.
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genericSet == null) ? 0 : genericSet.hashCode());
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
        GenericSet other = (GenericSet) obj;
        if (genericSet == null) {
            if (other.genericSet != null)
                return false;
        } else if (!genericSet.equals(other.genericSet))
            return false;
        return true;
    }

    @Override
    public String toString() {
        // NOTE: This assumes that toArray is run once, since we have no guarantee
        // on the order in which elements are returned.
        // AF(c) = { c.genericSet.toArray[i].intValue | 0 <= i < c.genericSet.size }

        StringBuilder buf = new StringBuilder();
        buf.append("{");
        boolean firstItem = true;
        for (E i : genericSet) {
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
        // RI = The genericSet is not null and no elements of genericSet are null and there are
        //      no duplicates in the genericSet and genericSet.size > 1

        // NOTE: This is established by the constructor, but we can still check it here.
        if (genericSet == null) {
            return false;
        }

        for (E num : genericSet) {
            if (num == null) {
                return false;
            }
        }

        // NOTE: The HashSet already takes care of duplicates, but if we wanted to check
        // for them anyway, we would want to do something like the following
        HashSet<E> seenBefore = new HashSet<>();
        for (E num : genericSet) {
            if (seenBefore.contains(num)) {
                return false;
            }
            seenBefore.add(num);
        }

        return true;
    }

    @Override
    public Iterator<E> getStandardIterator() {
        return new StandardSetIterator<>(genericSet);
    }

    @Override
    public Iterator<E> getSortedIterator() {
        return new SortedSetIterator<>(genericSet);
    }

    @Override
    public Iterator<Integer> getEvenIterator() {
        List<E> plist = new ArrayList<>(genericSet);
        HashSet<Integer> pset = new HashSet<>();
        for (E aPlist : plist) {
            if (aPlist instanceof Integer) {
                pset.add((Integer) aPlist);
            }
        }
        return new EvenSetIterator(pset);
    }

    private static class StandardSetIterator<E> implements Iterator<E> {
        private final List<E> localSetAsList;
        private int currentIndex;

        StandardSetIterator(Set<E> intSet) {
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
        public E next() {
            if (currentIndex < localSetAsList.size()) {
                return localSetAsList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the standard set iterator: StandardSetIterator.next");
            }
        }
    }

    private static class SortedSetIterator<E extends Comparable<E>> implements Iterator<E> {
        private final List<E> localSetAsList;
        private int currentIndex;

        SortedSetIterator(Set<E> intSet) {
            List<E> l = new ArrayList<>(intSet);
            Collections.sort(l);
            localSetAsList = l;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localSetAsList.size();
        }

        @Override
        public E next() {
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

        EvenSetIterator(Set<Integer> intSet) {
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
