package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ecu.cs.seng6245.values.impl.ValueFactory;
import org.junit.Test;

public class StandardSetIteratorTest {
    @Test
    public void emptySetHasNoElements() {
        ISet<Integer> emptySet = ValueFactory.getValueFactory().makeIntegerSet();
        Iterator<Integer> iter = emptySet.getStandardIterator();
        assertFalse("Empty set gives false for hasNext", iter.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void emptySetThrowsOnNext() {
        ISet<Integer> emptySet = ValueFactory.getValueFactory().makeIntegerSet();
        Iterator<Integer> iter = emptySet.getStandardIterator();
        iter.next();
    }

    @Test
    public void singletonSetHasOneElement() {
        ISet<Integer> singletonSet = ValueFactory.getValueFactory().makeIntegerSet().insert(5);
        Iterator<Integer> iter = singletonSet.getStandardIterator();
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
    public void regularSetHasCorrectElements() {
        ISet<Integer> regularSet = ValueFactory.getValueFactory().makeIntegerSet().insert(5).insert(1).insert(3).insert(2).insert(4);
        Iterator<Integer> iter = regularSet.getStandardIterator();
        HashSet<Integer> yetToSee = new HashSet<>();
        yetToSee.add(1); yetToSee.add(2); yetToSee.add(3); yetToSee.add(4); yetToSee.add(5);
        while (iter.hasNext()) {
            int i = iter.next();
            assertTrue("Element has not yet been returned by the iterator", yetToSee.contains(i));
            yetToSee.remove(i);
        }
        assertTrue("All elements have been seen", yetToSee.isEmpty());
        assertFalse("Set has no more elements", iter.hasNext());
    }
}
