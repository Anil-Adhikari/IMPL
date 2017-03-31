package edu.ecu.cs.seng6245.values;

import edu.ecu.cs.seng6245.imp.exceptions.EmptyListException;
import edu.ecu.cs.seng6245.values.impl.ValueFactory;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by anil on 3/31/17.
 */
public class HomeWork6Test {
    @Test
    public void getHeadFromNonEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Getting head from the list", 1,ilist.head().intValue());
    }

    @Test(expected = EmptyListException.class)
    public void getHeadFromEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("Getting head from the empty list", 1,ilist.head().intValue());
    }

    @Test
    public void getTailFromNonEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Getting tail from the list", "[2, 3]",ilist.tail().toString());
        assertEquals("Size of tail is one less than original list",ilist.size()-1,ilist.tail().size().intValue());
    }

    @Test(expected = EmptyListException.class)
    public void getTailFromEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("Getting tail from the empty list", "",ilist.tail().toString());
    }

    @Test
    public void unionOfTwoGenericSetTest(){
        ISet<Integer> iset1 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> iset2 = ValueFactory.getValueFactory().makeIntegerSet().insert(3).insert(4).insert(5);
        assertEquals("This contains both the common and non common element", "{1, 2, 3, 4, 5}",iset1.union(iset2).toString());
        assertTrue("Union set contains 5",iset1.union(iset2).toString().contains("5"));
    }

    @Test
    public void intersectionOfTwoGenericSetTest(){
        ISet<Integer> iset1 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> iset2 = ValueFactory.getValueFactory().makeIntegerSet().insert(3).insert(4).insert(5);
        assertEquals("3 is the only common element", "{3}",iset1.intersection(iset2).toString());
        assertTrue("Intersection set contains 3",iset1.intersection(iset2).toString().contains("3"));
    }

    @Test
    public void subsetOfTest(){
        ISet<Integer> iset1 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> iset2 = ValueFactory.getValueFactory().makeIntegerSet();
        assertTrue("Empty set is subset of every set",iset2.subsetOf(iset1));
        ISet<Integer> iset3 = iset2.insert(1).insert(3);
        assertTrue("iset3 is subset of iset1", iset3.subsetOf(iset1));
        assertFalse("iset1 is not subset of iset3", iset1.subsetOf(iset3));
    }

    @Test
    public void evenSetGeneratorOnlyWorksonIntegerTest(){
        ISet<String> iset1 = ValueFactory.getValueFactory().makeStringSet().insert("Nepal").insert("Home").insert("Love");
        Iterator<Integer> itr = iset1.getEvenIterator();
        ISet<String> iset2 = ValueFactory.getValueFactory().makeStringSet();
        while(itr.hasNext()){
            iset2.insert(itr.next().toString());
        }
        assertEquals("size of iset2 is 0", 0,iset2.size().intValue());
    }

    @Test
    public void stringListTest(){
        IList<String> ilist = ValueFactory.getValueFactory().makeStringList().insertAtEnd("I").insertAtEnd("Me").insertAtEnd("My");
        assertTrue("list contains 'I'", ilist.toString().contains("I"));
    }

    @Test
    public void booleanSetTest(){
        ISet<Boolean> set = ValueFactory.getValueFactory().makeBooleanSet().insert(true).insert(false);
        assertTrue("set contains true", set.toString().contains("true"));
    }

    @Test
    public void booleanListTest(){
        IList<Boolean> set = ValueFactory.getValueFactory().makeBooleanList().insertAtEnd(false).insertAtEnd(false);
        assertFalse("set contains true", set.toString().contains("true"));
    }
}
