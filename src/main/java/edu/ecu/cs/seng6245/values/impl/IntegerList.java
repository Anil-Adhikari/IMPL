package edu.ecu.cs.seng6245.values.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;
import edu.ecu.cs.seng6245.values.IList;

/*
 * Implementation of a mutable list of integers.
 *
 */
class IntegerList implements IList<Integer> {
    /** The internal representation of the integer list */
    private final LinkedList<Integer> intList;

    /**
     * Create a new, empty list of integers.
     */
    protected IntegerList() {
        intList = new LinkedList<>();
    }

    @Override
    public IList<Integer> insertAtStart(Integer i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");
        intList.addFirst(i);
        return this;
    }

    @Override
    public IList<Integer> insertAtEnd(Integer i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");
        intList.addLast(i);
        return this;
    }

    @Override
    public IList<Integer> remove(Integer i) {
        if (i == null) throw new NullPointerException("Element to remove cannot be null");
        //noinspection StatementWithEmptyBody
        while (intList.remove(i));
        return this;
    }

    @Override
    public boolean in(Integer i) {
        if (i == null) throw new NullPointerException("Element to check for membership cannot be null");
        return intList.contains(i);
    }

    @Override
    public Integer size() {
        return intList.size();
    }

    @Override
    public Integer get(Integer idx) {
        if (idx == null) throw new NullPointerException("Index idx cannot be null");
        if (idx > 0 && idx <= intList.size()) {
            return intList.get(idx-1);
        } else {
            throw new ListIndexException("Cannot get out of bounds index", idx, intList.size());
        }
    }

    @Override
    public Integer head() {
        // TODO Add the code for head here.
        return null;
    }

    @Override
    public IList<Integer> tail() {
        // TODO Add the code for tail here.
        return null;
    }

    @Override
    public String toString() {
        // AF(c) = [ c.intList[i].intValue | 0 <= i < c.intList.size ]

        StringBuilder buf = new StringBuilder();
        buf.append("[");
        boolean firstItem = true;
        for (Integer i : intList) {
            if (!firstItem) {
                buf.append(", ");
            } else {
                firstItem = false;
            }
            buf.append(i);
        }
        buf.append("]");
        return buf.toString();
    }

    public boolean equals(Object o) {
        // Since list is mutable, we don't need to implement equals. To show that we didn't
        // forget it, we can just pass on the parameter to super.equals, which is the
        // version provided by Object (which is what would get called if we didn't have
        // this here).
        return super.equals(o);
    }

    /**
     * Check the representation to make sure the invariant holds.
     *
     * @return true when the invariant holds, false otherwise
     */
    @SuppressWarnings("ConstantConditions")
    public boolean repOk() {
        // RI = The intList is not null and no elements of intList are null

        // NOTE: This is established by the constructor, but we can still check it here.
        if (intList == null) {
            return false;
        }

        for (Integer num : intList) {
            if (num == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<Integer> getStandardIterator() {
        return new StandardListIterator(intList);
    }

    @Override
    public Iterator<Integer> getUniqueIterator() {
        return new UniqueListIterator(intList);
    }

    @Override
    public Iterator<Integer> getSortedIterator() {
        return new SortedListIterator(intList);
    }

    private static class StandardListIterator implements Iterator<Integer> {
        private int currentIndex;
        private final List<Integer> localList;

        public StandardListIterator(List<Integer> intList) {
            localList = intList;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex < localList.size()) {
                return localList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the standard list iterator: StandardListIterator.next");
            }
        }
    }

    private static class UniqueListIterator implements Iterator<Integer> {
        private final List<Integer> localList;
        private HashSet<Integer> seenBefore;
        private int currentIndex = 0;

        public UniqueListIterator(List<Integer> intList) {
            localList = intList;
            seenBefore = new HashSet<>();
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex >= localList.size()) {
                throw new NoSuchElementException("No next element in the unique list iterator: UniqueListIterator.next");
            }
            Integer res = localList.get(currentIndex++);
            seenBefore.add(res);
            while (currentIndex < localList.size() && seenBefore.contains(localList.get(currentIndex))) {
                ++currentIndex;
            }
            return res;
        }
    }

    private static class SortedListIterator implements Iterator<Integer> {
        private final List<Integer> localList;
        private int currentIndex;

        public SortedListIterator(List<Integer> intList) {
            List<Integer> l = new LinkedList<>(intList);
            // This sorts in place, so we really do need to make the copy above
            Collections.sort(l);
            localList = l;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public Integer next() {
            if (currentIndex < localList.size()) {
                return localList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the sorted list iterator: SortedListIterator.next");
            }
        }
    }
}
