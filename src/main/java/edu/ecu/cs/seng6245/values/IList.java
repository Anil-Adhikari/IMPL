package edu.ecu.cs.seng6245.values;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;
import edu.ecu.cs.seng6245.imp.exceptions.EmptyListException;

/**
 * Mutable lists of values. Note that indexes are 1-based, not 0-based, so the first
 * element is at 1, the second is at 2, etc.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public interface IList<E> {
    /**
     * Insert the given value at the start of the list.
     *
     * @param element the value to insert
     *
     * @return the existing list
     *
     * @throws NullPointerException when element is null
     */
    IList<E> insertAtStart(E element);

    /**
     * Insert the given value at the end of the list.
     *
     * @param element the value to insert
     *
     * @return the existing list
     *
     * @throws NullPointerException when element is null
     */
    IList<E> insertAtEnd(E element);

    /**
     * Remove the given value from the list.
     *
     * @param element the value to remove
     *
     * @return the existing list
     *
     * @throws NullPointerException when element is null
     */
    IList<E> remove(E element);

    /**
     * Check to see if a given value is in the list.
     *
     * @param element the value being checked for membership in the list
     *
     * @return true when element is in the list, false otherwise
     *
     * @throws NullPointerException when element is null
     */
    boolean in(E element);

    /**
     * Get the size of the list.
     *
     * @return the size of the list.
     */
    Integer size();

    /**
     * Return the value at index idx.
     *
     * @param idx the index to look up.
     *
     * @return the value at index idx
     *
     * @throws ListIndexException if the index is out of the range of indexes for the list, including when the list is empty.
     * @throws NullPointerException when idx is null
     */
    E get(Integer idx);

    /**
     * Return the first element of the list. For instance, the head of
     * list {@code [2,5,7]} is the number {@code 2}.
     *
     * @return the first element of the list.
     *
     * @throws EmptyListException if the list is empty
     */
    E head();

    /**
     * Return the list, except for the first element. For instance, the
     * tail of the list {@code [2,5,7]} is the list {@code [5,7]}. The
     * tail of a list with only one element is the empty list.
     *
     * @return the list, except for the first element; singleton lists yield the empty list
     *
     * @throws EmptyListException if the list is empty
     */
    IList<E> tail();

    /**
     * Return an iterator that provides the elements in the list in
     * list order. For list {@code [1,2,5,3]}, this would be {@code 1},
     * then {@code 2}, then {@code 5}, then {@code 3}.
     *
     * @return the iterator over the list elements
     */
    Iterator<E> getStandardIterator();

    /**
     * Return an iterator that provides the elements in the list in standard
     * order, but that does not return duplicates of numbers that have already
     * been returned. For list {@code [1,2,2,5,2,3,1]}, the iterator would
     * return {@code 1}, {@code 2}, {@code 5}, then {@code 3}.
     *
     * @return the iterator over the list elements
     */
    Iterator<E> getUniqueIterator();

    /**
     * Return an iterator that provides the elements in the list in a
     * sorted order, from the smallest to the largest element.
     *
     * @return the iterator over the list elements
     */
    Iterator<E> getSortedIterator();
}
