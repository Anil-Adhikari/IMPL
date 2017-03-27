package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ecu.cs.seng6245.values.impl.ValueFactory;
import org.junit.Test;

public class SortedListIteratorTest {

	@Test
	public void emptyListHasNoElements() {
		IList<Integer> emptyList = ValueFactory.getValueFactory().makeIntegerList();
		Iterator<Integer> iter = emptyList.getSortedIterator();
		assertFalse("Empty list gives false for hasNext", iter.hasNext());
	}

	@Test(expected=NoSuchElementException.class)
	public void emptyListThrowsOnNext() {
		IList<Integer> emptyList = ValueFactory.getValueFactory().makeIntegerList();
		Iterator<Integer> iter = emptyList.getSortedIterator();
		iter.next();
	}

	@Test
	public void singletonListHasOneElement() {
		IList<Integer> singletonList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5);
		Iterator<Integer> iter = singletonList.getSortedIterator();
		assertTrue("Singleton list has one element", iter.hasNext());
		Integer i = iter.next();
		assertEquals("The single element is 5", 5, i.intValue());
		assertFalse("Singleton list has no more elements", iter.hasNext());
	}
	
	@Test
	public void sortedListHasCorrectElements() {
		IList<Integer> regularList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(1).insertAtEnd(3).insertAtEnd(2).insertAtEnd(4);
		Iterator<Integer> iter = regularList.getSortedIterator();
		assertEquals("The first element is 1", 1, iter.next().intValue());
		assertEquals("The second element is 2", 2, iter.next().intValue());
		assertEquals("The third element is 3", 3, iter.next().intValue());
		assertEquals("The fourth element is 4", 4, iter.next().intValue());
		assertEquals("The fifth element is 5", 5, iter.next().intValue());
		assertFalse("List has no more elements", iter.hasNext());
	}

	@Test
	public void sortedListStillHasDupes() {
		IList<Integer> regularList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(1).insertAtEnd(5).insertAtEnd(1).insertAtEnd(2);
		Iterator<Integer> iter = regularList.getSortedIterator();
		assertEquals("The first element is 1", 1, iter.next().intValue());
		assertEquals("The second element is 1", 1, iter.next().intValue());
		assertEquals("The third element is 2", 2, iter.next().intValue());
		assertEquals("The fourth element is 5", 5, iter.next().intValue());
		assertEquals("The fifth element is 5", 5, iter.next().intValue());
		assertFalse("List has no more elements", iter.hasNext());
	}

}
