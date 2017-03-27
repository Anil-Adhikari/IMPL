package edu.ecu.cs.seng6245.values;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.exceptions.EmptySetException;

/**
 * Immutable sets of values.
 *
 * @author Mark Hills
 * @version 1.0
 *
 */
public interface ISet<E> {

    /**
     * Insert the given value into the set.
     *
     * @param element the value to insert
     *
     * @return a new set including element
     *
     * @throws NullPointerException when element is null
     */
    ISet<E> insert(E element);

    /**
     * Remove element from the set, returning a new set without element
     *
     * @param element the value to remove from the set
     *
     * @return a new set without element; this will be the same as the current set if element is not present
     *
     * @throws NullPointerException when element is null
     */
    ISet<E> remove(E element);

    /**
     * Check to see if the given value is in the set
     *
     * @param element the value being checked for membership
     *
     * @return true if the value is in the set, false otherwise (including when the set is empty)
     *
     * @throws NullPointerException when element is null
     */
    boolean in(E element);

    /**
     * Get the size of the set
     *
     * @return the size of the set
     */
    Integer size();

    /**
     * Get an arbitrary element from the set.
     *
     * @return one of the members of the set
     * @throws EmptySetException when the set is empty
     */
    E getOneFrom();

    /**
     * Return the union of the current set and the parameter. For instance,
     * if {@code this} refers to the set {@code {1,2,3}}, and {@code set} refers
     * to the set {@code {3,4,5}}, the result will be {@code {1,2,3,4,5}}. In
     * general, the rule is that the union contains all the elements that are
     * present in either, or both, of the sets.
     *
     * @param set the set we are union-ing with the current set
     *
     * @return a set containing the members of either {@code this} or {@code set} or both
     *
     * @throws NullPointerException when set is null
     */
    ISet<E> union(ISet<E> set);

    /**
     * Return the intersection of the current set and the parameter. For instance,
     * if {@code this} refers to the set {@code {1,2,3}}, and {@code set} refers
     * to the set {@code {2,3,4}}, the result will be {@code {2,3}}. In general,
     * the rule is that the intersection contains all the elements that are present
     * in both sets.
     *
     * @param set the set we are intersecting with the current set
     *
     * @return a set containing the members of both {@code this} and {@code set}
     *
     * @throws NullPointerException when set is null
     */
    ISet<E> intersection(ISet<E> set);

    /**
     * Determine if {@code this} is a subset of {@code set}. For instance, if
     * {@code this} refers to the set {@code {2,3}}, and {@code set} refers to
     * the set {@code {2,3,4}}, the result will be {@code true}. In general,
     * the rule is that one set is a subset of another if every element in the
     * first is also in the second; a set is a subset of itself.
     *
     * @param set the set we are performing the subtype check against
     *
     * @return true if {@code this} is a subset of {@code set} otherwise false
     *
     * @throws NullPointerException when set is null
     */
    boolean subsetOf(ISet<E> set);

    /**
     * Return an iterator that provides the values in the set in an
     * arbitrary order.
     *
     * @return the iterator over the set elements
     */
    Iterator<E> getStandardIterator();

    /**
     * Return an iterator that provides the values in the set in a
     * sorted order, from the smallest to the largest numbers.
     *
     * @return the iterator over the set elements
     */
    Iterator<E> getSortedIterator();

    /**
     * Return an iterator that provides the values in the set in an
     * arbitrary order, but only returns even numbers including 0. For
     * instance, for a set {1,2,3,4,5}, only {@code 2} and {@code 4}
     * (with either {@code 2} then {@code 4} or {@code 4} then {@code 2})
     * should be returned. Note that this only works for cases where
     * the values are {@link Integer}s.
     *
     * @return the iterator over the set elements
     */
    Iterator<Integer> getEvenIterator();
}
