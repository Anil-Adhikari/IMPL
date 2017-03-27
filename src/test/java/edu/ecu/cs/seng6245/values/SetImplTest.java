package edu.ecu.cs.seng6245.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ecu.cs.seng6245.imp.exceptions.EmptySetException;
import edu.ecu.cs.seng6245.values.impl.ValueFactory;

public class SetImplTest {

    @Test
    public void sizeOfEmptySetIsZero() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        assertEquals("Size of empty set is 0", 0, is.size().intValue());
    }

    @Test
    public void sizeOfSingletonSetIsOne() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1);
        assertEquals("Size of singleton set is 1", 1, is.size().intValue());
    }

    public void sizeOfSingletonWithDupsIsOne() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(1);
        assertEquals("Size of singleton set is 1", 1, is.size().intValue());
    }

    @Test
    public void sizeOfLargerSetIsCorrect() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(2).insert(3).insert(4).insert(1);
        assertEquals("Size of larger set is 4", 4, is.size().intValue());
        assertTrue("Contains 1", is.in(1));
        assertTrue("Contains 2", is.in(2));
        assertTrue("Contains 3", is.in(3));
        assertTrue("Contains 4", is.in(4));
    }

    @Test
    public void dupsAreIgnored() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(1).insert(1);
        assertEquals("Size of set with dups is 1", 1, is.size().intValue());
    }

    @Test
    public void removesActuallyRemoves() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertEquals("Size of set before remove is 3", 3, is.size().intValue());
        ISet<Integer> isAfter = is.remove(2);
        assertEquals("Size of set after remove is 2", 2, isAfter.size().intValue());
        assertEquals("Size of original set is still 3", 3, is.size().intValue());
        assertTrue("Contains 1", isAfter.in(1));
        assertTrue("Contains 3", isAfter.in(3));
        assertTrue("Before still contains 2", is.in(2));
    }

    @Test
    public void removesOfNonMemberRemovesNothing() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertEquals("Size of set before remove is 3", 3, is.size().intValue());
        is = is.remove(4);
        assertEquals("Size of set after remove is 3", 3, is.size().intValue());
        assertTrue("Contains 1", is.in(1));
        assertTrue("Contains 2", is.in(2));
        assertTrue("Contains 3", is.in(3));
    }

    @Test
    public void removesNotInfluencedByDups() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(1);
        assertEquals("Size of set before remove is 2", 2, is.size().intValue());
        is = is.remove(1);
        assertEquals("Size of set after remove is 1", 1, is.size().intValue());
        assertTrue("Contains 2", is.in(2));
        assertFalse("Does not contains 1", is.in(1));
    }

    @Test(expected=EmptySetException.class)
    public void getOneFromEmptyThrows() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        is.getOneFrom();
    }

    @Test
    public void getOneFromSingletonGetsSingletonValue() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(99);
        Integer i = is.getOneFrom();
        assertEquals("getOneFrom on singleton returns singleton item", 99, i.intValue());
    }

    @Test
    public void getOneFromPicksOneMember() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        int i = is.getOneFrom();
        assertTrue("getOneFrom on set returns a set item", i == 1 || i == 2 || i == 3);
    }

    @Test
    public void toStringForEmptySet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        assertEquals("toString works for empty sets",  "{}", is.toString());
    }

    @Test
    public void toStringForSingletonSet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(99);
        assertEquals("toString works for singleton sets",  "{99}", is.toString());
    }

    @Test
    public void toStringForNormalSet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertTrue("first character is {", is.toString().startsWith("{"));
        assertTrue("last character is }", is.toString().endsWith("}"));
        assertTrue("contains 1", is.toString().contains("1"));
        assertTrue("contains 2", is.toString().contains("2"));
        assertTrue("contains 3", is.toString().contains("3"));
    }

    @Test
    public void inForMembersIsTrue() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertTrue("2 in {1,2,3}", is.in(2));
    }

    @Test
    public void inForNonMembersIsFalse() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertFalse("4 notin {1,2,3}", is.in(4));
    }

    @Test
    public void setIsEqualToItself() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> is2 = is;
        assertTrue("references to the same set are equal", is.equals(is2));
    }

    @Test
    public void setIsEqualToDistinctWithSameElements() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> is2 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        assertTrue("distinct sets with the same elements are equal", is.equals(is2));
    }

    @Test
    public void setIsNotEqualForDifferent() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> is2 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(4);
        assertFalse("different sets are not equal", is.equals(is2));
    }

    @Test
    public void inForEmptySetIsFalse() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        assertFalse("nothing is in the empty set", is.in(3));
    }

    @Test
    public void removeFromEmptySetReturnsEmptySet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        ISet<Integer> is2 = is.remove(3);
        assertTrue("Removing from the empty set just gives back the empty set", is.equals(is2));
    }

    @Test
    public void removeFromSingletonReturnsEmptySet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(3);
        ISet<Integer> is2 = is.remove(3);
        assertTrue("Removing from the singleton set gives back the empty set", is2.equals(ValueFactory.getValueFactory().makeIntegerSet()));
    }

    @Test
    public void removeNonElementFromSingletonSetReturnsSingletonSet() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet().insert(3);
        ISet<Integer> is2 = is.remove(4);
        assertTrue("Removing a non-element from the singleton set just gives back an equal singleton set", is.equals(is2));
    }

    @Test(expected=NullPointerException.class)
    public void insertingNullInEmptySetThrows() {
        ISet<Integer> is = ValueFactory.getValueFactory().makeIntegerSet();
        is.insert(null);
    }

    @Test(expected=NullPointerException.class)
    public void insertingNullInSingletonSetThrows() {
        ISet<Integer> is2 = ValueFactory.getValueFactory().makeIntegerSet().insert(1);
        is2.insert(null);
    }

    @Test(expected=NullPointerException.class)
    public void insertingNullInRegularSetThrows() {
        ISet<Integer> is3 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        is3.insert(null);
    }

    @Test(expected=NullPointerException.class)
    public void removingNullInRegularSetThrows() {
        ISet<Integer> is3 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        is3.remove(null);
    }

    @Test(expected=NullPointerException.class)
    public void checkingMembershipForNullInRegularSetThrows() {
        ISet<Integer> is3 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        is3.in(null);
    }
}
