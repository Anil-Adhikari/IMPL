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
        assertEquals("Head of the list", 1,ilist.head().intValue());
    }

    @Test(expected = EmptyListException.class)
    public void getHeadFromEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("Head of empty list", 1,ilist.head().intValue());
    }

    @Test
    public void getTailFromNonEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList().insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        assertEquals("Tail of the list", "[2, 3]",ilist.tail().toString());
    }

    @Test(expected = EmptyListException.class)
    public void getTailFromEmptyGenericListTest(){
        IList<Integer> ilist = ValueFactory.getValueFactory().makeIntegerList();
        assertEquals("Tail of empty list", "",ilist.tail().toString());
    }

    @Test
    public void unionOfTwoGenericSetTest(){
        ISet<Integer> iset1 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> iset2 = ValueFactory.getValueFactory().makeIntegerSet().insert(3).insert(4).insert(5);
        assertEquals("Union set contains both common and uncommon elements", "{1, 2, 3, 4, 5}",iset1.union(iset2).toString());
        assertTrue("Union set contains 5",iset1.union(iset2).toString().contains("5"));
        assertEquals("Size of union set is 5",5,iset1.union(iset2).size().intValue());
    }

    @Test
    public void intersectionOfTwoGenericSetTest(){
        ISet<Integer> iset1 = ValueFactory.getValueFactory().makeIntegerSet().insert(1).insert(2).insert(3);
        ISet<Integer> iset2 = ValueFactory.getValueFactory().makeIntegerSet().insert(3).insert(4).insert(5).insert(1);
        assertEquals("3 is the only common element", "{1, 3}",iset1.intersection(iset2).toString());
        assertTrue("Intersection set contains 3",iset1.intersection(iset2).toString().contains("3"));
    }

    @Test
    public void subsetOfTest(){
        ISet<String> set1 = ValueFactory.getValueFactory().makeStringSet().insert("ABC").insert("BCD").insert("CDE");
        ISet<String> set2 = ValueFactory.getValueFactory().makeStringSet();
        assertTrue("Empty set is subset of every set",set2.subsetOf(set1));
        ISet<String> set3 = set2.insert("BCD").insert("ABC");
        assertTrue("iset3 is subset of iset1", set3.subsetOf(set1));
        assertFalse("iset1 is not subset of iset3.", set1.subsetOf(set3));
    }

    @Test
    public void evenSetGeneratorOnlyWorksonIntegerTest(){
        ISet<String> iset1 = ValueFactory.getValueFactory().makeStringSet().insert("asd").insert("bnm").insert("cxv");
        Iterator<Integer> itr = iset1.getEvenIterator();
        ISet<String> iset2 = ValueFactory.getValueFactory().makeStringSet();
        while(itr.hasNext()){
            iset2.insert(itr.next().toString());
        }
        assertEquals("size of iset2 is 0", 0,iset2.size().intValue());
    }

    @Test
    public void stringListTest(){
        IList<String> slist = ValueFactory.getValueFactory().makeStringList().insertAtEnd("Nepal").insertAtEnd("is").insertAtEnd("Beautiful");
        assertTrue("list contains 'I'", slist.toString().contains("Nepal"));
    }

    @Test
    public void booleanSetTest(){
        ISet<Boolean> set = ValueFactory.getValueFactory().makeBooleanSet().insert(true).insert(false);
        assertTrue("set contains true", set.toString().contains("true"));
    }

    @Test
    public void booleanListTest(){
        IList<Boolean> list = ValueFactory.getValueFactory().makeBooleanList().insertAtEnd(false).insertAtEnd(false);
        assertFalse("set contains true", list.toString().contains("true"));
        assertEquals("size of the booleanlist",2,list.size().intValue());
    }

    @Test
    public void subSetOfBooleanSet(){
        ISet<Boolean> bset = ValueFactory.getValueFactory().makeBooleanSet().insert(true).insert(false).insert(true);
        ISet<Boolean> b1set = ValueFactory.getValueFactory().makeBooleanSet().insert(true);
        assertTrue("Subset of boolean set",b1set.subsetOf(bset));
    }
}
