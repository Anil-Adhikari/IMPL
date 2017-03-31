package edu.ecu.cs.seng6245.values;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EvenSetIteratorTest.class, ListImplTest.class,
		SetImplTest.class, SortedListIteratorTest.class,
		SortedSetIteratorTest.class, StandardListIteratorTest.class,
		StandardSetIteratorTest.class, UniqueListIteratorTest.class, HomeWork6Test.class })
public class AllTests {

}
