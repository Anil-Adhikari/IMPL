package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ecu.cs.seng6245.values.impl.ValueFactory;
import org.junit.Test;

public class UniqueListIteratorTest {
	@Test
	public void emptyListHasNoElements() {
		IList<Integer> emptyList = ValueFactory.getValueFactory().makeIntegerList();
		Iterator<Integer> iter = emptyList.getUniqueIterator();
		assertFalse("Empty list gives false for hasNext", iter.hasNext());
	}

	@Test(expected=NoSuchElementException.class)
	public void emptyListThrowsOnNext() {
		IList<Integer> emptyList = ValueFactory.getValueFactory().makeIntegerList();
		Iterator<Integer> iter = emptyList.getUniqueIterator();
		iter.next();
	}

	@Test
	public void singletonListHasOneElement() {
		IList<Integer> singletonList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5);
		Iterator<Integer> iter = singletonList.getUniqueIterator();
		assertTrue("Singleton list has one element", iter.hasNext());
		Integer i = iter.next();
		assertEquals("The single element is 5", 5, i.intValue());
		assertFalse("Singleton list has no more elements", iter.hasNext());
	}

	@Test
	public void duplicateListHasOneElement() {
		IList<Integer> duplicateList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(5).insertAtEnd(5).insertAtEnd(5).insertAtEnd(5);
		Iterator<Integer> iter = duplicateList.getUniqueIterator();
		assertTrue("Duplicate list has one element", iter.hasNext());
		Integer i = iter.next();
		assertEquals("The single element is 5", 5, i.intValue());
		assertFalse("Duplicate list has no more elements", iter.hasNext());
	}

	@Test
	public void regularListHasCorrectElements() {
		IList<Integer> regularList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(1).insertAtEnd(3).insertAtEnd(2).insertAtEnd(4);
		Iterator<Integer> iter = regularList.getUniqueIterator();
		assertEquals("The first element is 5", 5, iter.next().intValue());
		assertEquals("The second element is 1", 1, iter.next().intValue());
		assertEquals("The third element is 3", 3, iter.next().intValue());
		assertEquals("The fourth element is 2", 2, iter.next().intValue());
		assertEquals("The fifth element is 4", 4, iter.next().intValue());
		assertFalse("List has no more elements", iter.hasNext());
	}

	@Test
	public void regularListDupesNotShown() {
		IList<Integer> regularList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(1).insertAtEnd(1).insertAtEnd(1).insertAtEnd(2);
		Iterator<Integer> iter = regularList.getUniqueIterator();
		assertEquals("The first element is 5", 5, iter.next().intValue());
		assertEquals("The second element is 1", 1, iter.next().intValue());
		assertEquals("The third element is 2", 2, iter.next().intValue());
		assertFalse("List has no more elements", iter.hasNext());
	}		

	@Test
	public void regularListDupesAtEndWorks() {
		IList<Integer> regularList = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(5).insertAtEnd(1).insertAtEnd(1).insertAtEnd(1).insertAtEnd(2).insertAtEnd(1);
		Iterator<Integer> iter = regularList.getUniqueIterator();
		assertEquals("The first element is 5", 5, iter.next().intValue());
		assertEquals("The second element is 1", 1, iter.next().intValue());
		assertEquals("The third element is 2", 2, iter.next().intValue());
		assertFalse("List has no more elements", iter.hasNext());
	}		
}
