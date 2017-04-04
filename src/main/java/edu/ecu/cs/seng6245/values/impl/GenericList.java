package edu.ecu.cs.seng6245.values.impl;

import java.util.*;

import edu.ecu.cs.seng6245.imp.exceptions.EmptyListException;
import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;
import edu.ecu.cs.seng6245.values.IList;

/*
 * Implementation of a mutable list of integers.
 *
 */
class GenericList<E extends Comparable<E>> implements IList<E> {
    /** The internal representation of the integer list */
    private final LinkedList<E> genericList;

    /**
     * Create a new, empty list of integers.
     */
    GenericList() {
        genericList = new LinkedList<>();
    }

    @Override
    public IList<E> insertAtStart(E i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");
        genericList.addFirst(i);
        return this;
    }

    @Override
    public IList<E> insertAtEnd(E i) {
        if (i == null) throw new NullPointerException("Element to insert cannot be null");
        genericList.addLast(i);
        return this;
    }

    @Override
    public IList<E> remove(E i) {
        if (i == null) throw new NullPointerException("Element to remove cannot be null");
        //noinspection StatementWithEmptyBody
        while (genericList.remove(i));
        return this;
    }

    @Override
    public boolean in(E i) {
        if (i == null) throw new NullPointerException("Element to check for membership cannot be null");
        return genericList.contains(i);
    }

    @Override
    public Integer size() {
        return genericList.size();
    }

    @Override
    public E get(Integer idx) {
        if (idx == null) throw new NullPointerException("Index idx cannot be null");
        if (idx > 0 && idx <= genericList.size()){
            return genericList.get(idx - 1);
        }
        else {
            throw new ListIndexException("Cannot get out of bounds index", idx, genericList.size());
        }
    }

    @Override
    public E head() {
        // Returns the first element if the set is not empty, otherwise throws an EmptyListException
        if(genericList.isEmpty()) throw new EmptyListException("The list is empty.");
        return genericList.get(0);
    }

    @Override
    public IList<E> tail() {
        // Returns the list except first element if the set is not empty, otherwise throws an EmptyListException
        if(genericList.isEmpty()) throw new EmptyListException("The list is empty.");
        GenericList<E> pg = new GenericList<>();
        for(int i = 1; i < this.size();i++){
            pg.insertAtEnd(this.genericList.get(i));
        }
        return pg;
    }

    @Override
    public String toString() {
        // AF(c) = [ c.genericList[i].intValue | 0 <= i < c.genericList.size ]

        StringBuilder buf = new StringBuilder();
        buf.append("[");
        boolean firstItem = true;
        for (E i : genericList) {
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
        // RI = The genericList is not null and no elements of genericList are null

        // NOTE: This is established by the constructor, but we can still check it here.
        if (genericList == null) {
            return false;
        }

        for (E num : genericList) {
            if (num == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<E> getStandardIterator() {
        return new StandardListIterator<>(genericList);
    }

    @Override
    public Iterator<E> getUniqueIterator() {
        return new UniqueListIterator<>(genericList);
    }

    @Override
    public Iterator<E> getSortedIterator() {
        return new SortedListIterator<>(genericList);
    }

    private static class StandardListIterator<E> implements Iterator<E> {
        private int currentIndex;
        private final List<E> localList;

        StandardListIterator(List<E> intList) {
            localList = intList;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public E next() {
            if (currentIndex < localList.size()) {
                return localList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the standard list iterator: StandardListIterator.next");
            }
        }
    }

    private static class UniqueListIterator<E> implements Iterator<E> {
        private final List<E> localList;
        private HashSet<E> seenBefore;
        private int currentIndex = 0;

        UniqueListIterator(List<E> intList) {
            localList = intList;
            seenBefore = new HashSet<>();
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public E next() {
            if (currentIndex >= localList.size()) {
                throw new NoSuchElementException("No next element in the unique list iterator: UniqueListIterator.next");
            }
            E res = localList.get(currentIndex++);
            seenBefore.add(res);
            while (currentIndex < localList.size() && seenBefore.contains(localList.get(currentIndex))) {
                ++currentIndex;
            }
            return res;
        }
    }

    private static class SortedListIterator<E extends Comparable<E>> implements Iterator<E> {
        private final List<E> localList;
        private int currentIndex;

        SortedListIterator(List<E> intList) {
            List<E> l = new LinkedList<>(intList);
            // This sorts in place, so we really do need to make the copy above
            Collections.sort(l); //sort only works on the type E which is comparable or extends generic Comparable
            localList = l;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < localList.size();
        }

        @Override
        public E next() {
            if (currentIndex < localList.size()) {
                return localList.get(currentIndex++);
            } else {
                throw new NoSuchElementException("No next element in the sorted list iterator: SortedListIterator.next");
            }
        }
    }
}
