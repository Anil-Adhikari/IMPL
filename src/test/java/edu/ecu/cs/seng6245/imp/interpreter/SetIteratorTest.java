package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.assertEquals;

import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SetIteratorTest extends ImpTest {
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
    public void standardIteratorWithEmptySetHasNoElements() {
        String pgm = "begin xl = {} ; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for empty set iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorWithSingletonSetHasOneElement() {
        String pgm = "begin xl = {5}; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for {5} iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by standard iterator over {5} is 5", vf.makeInt(5), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorWithRegularSetHasCorrectElements() {
        String pgm = "begin xl = {5,1,3,2,4}; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Standard iterator for {5,1,3,2,4} iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the standard iterator for {5,1,3,2,4} is 5", vf.makeTrue(), runExpression("5 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the standard iterator for {5,1,3,2,4} is 1", vf.makeTrue(), runExpression("1 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the standard iterator for {5,1,3,2,4} is 3", vf.makeTrue(), runExpression("3 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the standard iterator for {5,1,3,2,4} is 2", vf.makeTrue(), runExpression("2 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the standard iterator for {5,1,3,2,4} is 4", vf.makeTrue(), runExpression("4 in yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void standardIteratorDoesNotModifySet() {
        String pgm = "begin xl = {5,1,3,2,4}; yl = []; foreach n in xl do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using standard iterator for {5,1,3,2,4}, the set is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 5", vf.makeTrue(), runExpression("5 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 1", vf.makeTrue(), runExpression("1 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 3", vf.makeTrue(), runExpression("3 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 2", vf.makeTrue(), runExpression("2 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 4", vf.makeTrue(), runExpression("4 in xl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithEmptySetHasNoElements() {
        String pgm = "begin xl = {}; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for empty set iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithSingletonSetHasOneElement() {
        String pgm = "begin xl = {5}; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for {5} iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by sorted iterator over {5} is 5", vf.makeTrue(), runExpression("5 in yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorWithSortedSetHasCorrectElements() {
        String pgm = "begin xl = {5,1,3,2,4}; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Sorted iterator for {5,1,3,2,4} iterates over 5 elements", vf.makeInt(5), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by the sorted iterator for {5,1,3,2,4} is 1", vf.makeInt(1), runExpression("yl[1]", interpreter.getCurrentEnvironment()));
        assertEquals("Second element returned by the sorted iterator for {5,1,3,2,4} is 2", vf.makeInt(2), runExpression("yl[2]", interpreter.getCurrentEnvironment()));
        assertEquals("Third element returned by the sorted iterator for {5,1,3,2,4} is 3", vf.makeInt(3), runExpression("yl[3]", interpreter.getCurrentEnvironment()));
        assertEquals("Fourth element returned by the sorted iterator for {5,1,3,2,4} is 4", vf.makeInt(4), runExpression("yl[4]", interpreter.getCurrentEnvironment()));
        assertEquals("Fifth element returned by the sorted iterator for {5,1,3,2,4} is 5", vf.makeInt(5), runExpression("yl[5]", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void sortedIteratorDoesNotModifySet() {
        String pgm = "begin xl = {5,1,3,2,4}; yl = []; foreach n in xl using sorted do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using sorted iterator for {5,1,3,2,4}, the set is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 5", vf.makeTrue(), runExpression("5 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 1", vf.makeTrue(), runExpression("1 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 3", vf.makeTrue(), runExpression("3 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 2", vf.makeTrue(), runExpression("2 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 4", vf.makeTrue(), runExpression("4 in xl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void evenIteratorWithEmptySetHasNoElements() {
        String pgm = "begin xl = {}; yl = []; foreach n in xl using even do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Even iterator for empty set iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void evenIteratorWithSingletonSetHasOneElement() {
        String pgm = "begin xl = {6}; yl = []; foreach n in xl using even do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Even iterator for {6} iterates over 1 element", vf.makeInt(1), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("First element returned by even iterator over {6} is 6", vf.makeTrue(), runExpression("6 in yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void evenIteratorWithAllOddsSetHasNoElements() {
        String pgm = "begin xl = {1,3,5,7,9,11,15,19}; yl = []; foreach n in xl using even do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Even iterator for {1,3,5,7,9,11,15,19} iterates over 0 elements", vf.makeInt(0), runExpression("#yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void evenIteratorWithRegularSetHasCorrectElements() {
        String pgm = "begin xl = {5,2,7,6,1,4,3}; yl = []; foreach n in xl using even do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("Even iterator for set {5,2,7,6,1,4,3} iterates over 3 elements", vf.makeInt(3), runExpression("#yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the even iterator for {5,2,7,6,1,4,3} is 2", vf.makeTrue(), runExpression("2 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the even iterator for {5,2,7,6,1,4,3} is 4", vf.makeTrue(), runExpression("4 in yl", interpreter.getCurrentEnvironment()));
        assertEquals("One of the elements returned by the even iterator for {5,2,7,6,1,4,3} is 6", vf.makeTrue(), runExpression("6 in yl", interpreter.getCurrentEnvironment()));
    }

    @Test
    public void evenIteratorDoesNotModifySet() {
        String pgm = "begin xl = {5,1,3,2,4}; yl = []; foreach n in xl using even do yl = yl + n; end";
        interpreter.interpret(pgm);
        assertEquals("After using even iterator for {5,1,3,2,4}, the set is still length 5", vf.makeInt(5), runExpression("#xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 5", vf.makeTrue(), runExpression("5 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 1", vf.makeTrue(), runExpression("1 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 3", vf.makeTrue(), runExpression("3 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 2", vf.makeTrue(), runExpression("2 in xl", interpreter.getCurrentEnvironment()));
        assertEquals("The set {5,1,3,2,4} still contains 4", vf.makeTrue(), runExpression("4 in xl", interpreter.getCurrentEnvironment()));
    }
}
