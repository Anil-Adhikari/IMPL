package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ecu.cs.seng6245.values.impl.ValueFactory;
import org.junit.Test;

public class SortedSetIteratorTest {
    @Test
    public void emptySetHasNoElements() {
        ISet<Integer> emptySet = ValueFactory.getValueFactory().makeIntegerSet();
        Iterator<Integer> iter = emptySet.getSortedIterator();
        assertFalse("Empty set gives false for hasNext", iter.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void emptySetThrowsOnNext() {
        ISet<Integer> emptySet = ValueFactory.getValueFactory().makeIntegerSet();
        Iterator<Integer> iter = emptySet.getSortedIterator();
        iter.next();
    }

    @Test
    public void singletonSetHasOneElement() {
        ISet<Integer> singletonSet = ValueFactory.getValueFactory().makeIntegerSet().insert(5);
        Iterator<Integer> iter = singletonSet.getSortedIterator();
        assertTrue("Singleton set has one element", iter.hasNext());
        Integer i = iter.next();
        assertEquals("The single element is 5", 5, i.intValue());
        assertFalse("Singleton set has no more elements", iter.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void singletonSetThrowsOnDoubleNext() {
        ISet<Integer> singletonSet = ValueFactory.getValueFactory().makeIntegerSet().insert(4);
        Iterator<Integer> iter = singletonSet.getStandardIterator();
        iter.next();
        iter.next();
    }

    @Test
    public void sortedSetHasCorrectElements() {
        ISet<Integer> sortedSet = ValueFactory.getValueFactory().makeIntegerSet().insert(5).insert(1).insert(3).insert(2).insert(4);
        Iterator<Integer> iter = sortedSet.getSortedIterator();
        assertEquals("The first element is 1", 1, iter.next().intValue());
        assertEquals("The second element is 2", 2, iter.next().intValue());
        assertEquals("The third element is 3", 3, iter.next().intValue());
        assertEquals("The fourth element is 4", 4, iter.next().intValue());
        assertEquals("The fifth element is 5", 5, iter.next().intValue());
        assertFalse("Set has no more elements", iter.hasNext());
    }
}
