package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class ListIteratorTest extends ImpTest {
    private static Interpreter interpreter;
    private static final ImpValueFactory vf = ImpValueFactory.getValueFactory();

    @BeforeClass
    public static void setUpClass() {
        interpreter = new Interpreter();
    }

    @Before
    public void setUp() {
        interpreter.resetInterpreter();
    }

    @Test
    public void standardIteratorWithEmptyListHasNoElements() {
        String pgm = "begin xl = []; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for empty list iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorWithSingletonListHasOneElement() {
        String pgm = "begin xl = [5]; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for [5] iterates over only 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by standard iterator over [5] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorWithRegularListHasCorrectElements() {
        String pgm = "begin xl = [5,1,3,2,4]; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for [5,1,3,2,4] iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by standard iterator over [5,1,3,2,4] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by standard iterator over [5,1,3,2,4] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by standard iterator over [5,1,3,2,4] is 3", vf.makeInt(3), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by standard iterator over [5,1,3,2,4] is 2", vf.makeInt(2), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by standard iterator over [5,1,3,2,4] is 4", vf.makeInt(4), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorWithRegularListStillHasDupes() {
        String pgm = "begin xl = [5,1,1,1,2]; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for [5,1,1,1,2] iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by standard iterator over [5,1,1,1,2] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by standard iterator over [5,1,1,1,2] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by standard iterator over [5,1,1,1,2] is 1", vf.makeInt(1), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by standard iterator over [5,1,1,1,2] is 1", vf.makeInt(1), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by standard iterator over [5,1,1,1,2] is 2", vf.makeInt(2), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorDoesNotModifyList() {
        String pgm = "begin xl = [5,1,1,1,2]; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using standard iterator for [5,1,1,1,2], the list is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The first element of list [5,1,1,1,2] is still 5", vf.makeInt(5), runExpression("xl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("The second element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("The third element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("The fourth element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("The fifth element of list [5,1,1,1,2] is still 2", vf.makeInt(2), runExpression("xl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithEmptyListHasNoElements() {
        String pgm = "begin xl = []; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for empty list iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithSingletonListHasOneElement() {
        String pgm = "begin xl = [5]; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for [5] iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by sorted iterator over [5] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithSortedListHasCorrectElements() {
        String pgm = "begin xl = [5,1,3,2,4]; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for [5,1,3,2,4] iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by sorted iterator over [5,1,3,2,4] is 1", vf.makeInt(1), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by sorted iterator over [5,1,3,2,4] is 2", vf.makeInt(2), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by sorted iterator over [5,1,3,2,4] is 3", vf.makeInt(3), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by sorted iterator over [5,1,3,2,4] is 4", vf.makeInt(4), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by sorted iterator over [5,1,3,2,4] is 5", vf.makeInt(5), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithSortedListStillHasDupes() {
        String pgm = "begin xl = [5,1,5,1,2]; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for [5,1,5,1,2] iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by sorted iterator over [5,1,5,1,2] is 1", vf.makeInt(1), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by sorted iterator over [5,1,5,1,2] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by sorted iterator over [5,1,5,1,2] is 2", vf.makeInt(2), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by sorted iterator over [5,1,5,1,2] is 5", vf.makeInt(5), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by sorted iterator over [5,1,5,1,2] is 5", vf.makeInt(5), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorDoesNotModifyList() {
        String pgm = "begin xl = [5,1,1,1,2]; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using sorted iterator for [5,1,1,1,2], the list is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The first element of list [5,1,1,1,2] is still 5", vf.makeInt(5), runExpression("xl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("The second element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("The third element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("The fourth element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("The fifth element of list [5,1,1,1,2] is still 2", vf.makeInt(2), runExpression("xl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithEmptyListHasNoElements() {
        String pgm = "begin xl = []; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for empty list iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithSingletonListHasOneElement() {
        String pgm = "begin xl = [5]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for [5] iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by unique iterator over [5] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithDuplicateListHasOneElement() {
        String pgm = "begin xl = [5,5,5,5,5]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for [5,5,5,5,5] iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by unique iterator over [5,5,5,5,5] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithRegularListHasCorrectElements() {
        String pgm = "begin xl = [5,1,3,2,4]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for [5,1,3,2,4] iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by unique iterator over [5,1,3,2,4] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by unique iterator over [5,1,3,2,4] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by unique iterator over [5,1,3,2,4] is 3", vf.makeInt(3), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by unique iterator over [5,1,3,2,4] is 2", vf.makeInt(2), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by unique iterator over [5,1,3,2,4] is 4", vf.makeInt(4), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithRegularListDupesNotShown() {
        String pgm = "begin xl = [5,1,1,1,2]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for [5,1,1,1,2] iterates over 3 elements", vf.makeInt(3), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by unique iterator over [5,1,1,1,2] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by unique iterator over [5,1,1,1,2] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by unique iterator over [5,1,1,1,2] is 2", vf.makeInt(2), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorWithRegularListDupesAtEndWorks() {
        String pgm = "begin xl = [5,1,1,1,2,1]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Unique iterator for [5,1,1,1,2,1] iterates over 3 elements", vf.makeInt(3), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by unique iterator over [5,1,1,1,2,1] is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by unique iterator over [5,1,1,1,2,1] is 1", vf.makeInt(1), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by unique iterator over [5,1,1,1,2,1] is 2", vf.makeInt(2), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void uniqueIteratorDoesNotModifyList() {
        String pgm = "begin xl = [5,1,1,1,2]; yl = []; foreach n in xl using unique do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using unique iterator for [5,1,1,1,2], the list is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The first element of list [5,1,1,1,2] is still 5", vf.makeInt(5), runExpression("xl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("The second element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("The third element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("The fourth element of list [5,1,1,1,2] is still 1", vf.makeInt(1), runExpression("xl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("The fifth element of list [5,1,1,1,2] is still 2", vf.makeInt(2), runExpression("xl[5]", interpreter.getCurrentEnvironment()));
    }
}
