package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;
import edu.ecu.cs.seng6245.values.impl.ValueFactory;

public class ListImplTest {

    @Test
    public void sizeOfEmptyListIsZero() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("Size of empty list is 0", 0, il.size().intValue());
    }

    @Test
    public void sizeOfSingletonListInsertAtStartIsOne() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1);
        assertEquals("Size of singleton list is 1", 1, il.size().intValue());
    }

    @Test
    public void sizeOfSingletonListInsertAtEndIsOne() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtEnd(1);
        assertEquals("Size of singleton list is 1", 1, il.size().intValue());
    }

    @Test
    public void sizeOfLargerListIsCorrect() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtEnd(2).insertAtEnd(3).insertAtEnd(4).insertAtStart(1);
        assertEquals("Size of larger list is 4", 4, il.size().intValue());
        assertEquals("Element 1 = 1", 1, il.get(1).intValue());
        assertEquals("Element 2 = 2", 2, il.get(2).intValue());
        assertEquals("Element 3 = 3", 3, il.get(3).intValue());
        assertEquals("Element 4 = 4", 4, il.get(4).intValue());
    }

    @Test
    public void insertAtStartInsertsAtStart() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtEnd(2);
        il.insertAtStart(1);
        assertEquals("Element 1 = 1", 1, il.get(1).intValue());
        assertEquals("Element 2 = 2", 2, il.get(2).intValue());
    }

    @Test
    public void insertAtEndInsertsAtEnd() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1);
        il.insertAtEnd(2);
        assertEquals("Element 1 = 1", 1, il.get(1).intValue());
        assertEquals("Element 2 = 2", 2, il.get(2).intValue());
    }

    @Test
    public void duplicatesAreAllowed() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(1).insertAtStart(1);
        assertEquals("Size of list with dups is 3", 3, il.size().intValue());
    }

    @Test
    public void removesActuallyRemoves() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Size of list before remove is 3", 3, il.size().intValue());
        il.remove(2);
        assertEquals("Size of list after remove is 2", 2, il.size().intValue());
        assertEquals("Remaining element 1 is 1", 1, il.get(1).intValue());
        assertEquals("Remaining element 2 is 3", 3, il.get(2).intValue());
    }

    @Test
    public void removesOfNonMemberRemovesNothing() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Size of list before remove is 3", 3, il.size().intValue());
        il.remove(4);
        assertEquals("Size of list after remove is 3", 3, il.size().intValue());
        assertEquals("Remaining element 1 is 1", 1, il.get(1).intValue());
        assertEquals("Remaining element 2 is 2", 2, il.get(2).intValue());
        assertEquals("Remaining element 3 is 3", 3, il.get(3).intValue());
    }

    @Test
    public void removesRemovesDuplicates() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(1);
        assertEquals("Size of list before remove is 3", 3, il.size().intValue());
        il.remove(1);
        assertEquals("Size of list after remove is 1", 1, il.size().intValue());
        assertEquals("Remaining element is 2", 2, il.get(1).intValue());
    }

    @Test(expected=ListIndexException.class)
    public void getForNegativeIndexThrows() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        il.get(-1);
    }

    @Test(expected=ListIndexException.class)
    public void getForZeroIndexThrows() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        il.get(0);
    }

    @Test(expected=ListIndexException.class)
    public void getForTooBigIndexThrows() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        il.get(4);
    }

    @Test
    public void getWorks() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Get works correctly", 2, il.get(2).intValue());
    }

    @Test
    public void toStringForEmptyList() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("toString works for empty lists",  "[]", il.toString());
    }

    @Test
    public void toStringForSingletonList() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(99);
        assertEquals("toString works for singleton lists",  "[99]", il.toString());
    }

    @Test
    public void toStringForNormalList() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("toString works for regular lists",  "[1, 2, 3]", il.toString());
    }

    @Test
    public void inForMemberIsTrue() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertTrue("2 in [1,2,3]", il.in(2));
    }

    @Test
    public void inForNonMemberIsFalse() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertFalse("4 notin [1,2,3]", il.in(4));
    }

    @Test
    public void listIsEqualToItself() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        IList<Integer> il2 = il;
        assertTrue("references to the same list are equal", il.equals(il2));
    }

    @Test
    public void listIsNotEqualForDistinct() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        IList<Integer> il2 = ValueFactory.getValueFactory().makeIntegerList();
        il2.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        assertFalse("distinct lists with the same elements are not equal", il.equals(il2));
    }

    @Test
    public void listIsNotEqualForDifferent() {
        IList<Integer> il = ValueFactory.getValueFactory().makeIntegerList();
        il.insertAtStart(1).insertAtEnd(2).insertAtEnd(3);
        IList<Integer> il2 = ValueFactory.getValueFactory().makeIntegerList();
        il2.insertAtStart(1).insertAtEnd(2).insertAtEnd(4);
        assertFalse("different lists are not equal", il.equals(il2));
    }

    @Test(expected=NullPointerException.class)
    public void checkingMembershipForNullInRegularListThrows() {
        IList<Integer> il3 = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        il3.in(null);
    }

    @Test(expected=NullPointerException.class)
    public void getWithNullIndexThrows() {
        IList<Integer> il3 = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        il3.get(null);
    }

    @Test(expected=NullPointerException.class)
    public void insertAtStartWithNullIndexThrows() {
        IList<Integer> il3 = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        il3.insertAtStart(null);
    }

    @Test(expected=NullPointerException.class)
    public void insertAtEndWithNullIndexThrows() {
        IList<Integer> il3 = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        il3.insertAtEnd(null);
    }

    @Test(expected=NullPointerException.class)
    public void removeWithNullIndexThrows() {
        IList<Integer> il3 = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        il3.remove(null);
    }
}
