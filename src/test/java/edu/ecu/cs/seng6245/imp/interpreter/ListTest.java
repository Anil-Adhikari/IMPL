package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ecu.cs.seng6245.imp.exceptions.ListIndexException;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class ListTest extends ImpTest {
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
    public void sizeOfEmptyListIsZero() {
        ImpValue iv = runExpression("#[]");
        assertNotNull(iv);
        assertEquals("#[] is 0", vf.makeInt(0), iv);
    }

    @Test
    public void sizeOfSingletonListIsOne() {
        ImpValue iv = runExpression("#[ 1 ]");
        assertNotNull(iv);
        assertEquals("#[ 1 ] is 1", vf.makeInt(1), iv);
    }

    @Test
    public void sizeOfBiggerListIsCorrect() {
        ImpValue iv = runExpression("#[ 1, 2, 3 ]");
        assertNotNull(iv);
        assertEquals("#[ 1, 2, 3 ] is 3", vf.makeInt(3), iv);
    }

    @Test
    public void dupsAreIncluded() {
        ImpValue iv = runExpression("#[ 1, 2, 2, 3 ]");
        assertNotNull(iv);
        assertEquals("#[ 1, 2, 2, 3 ] is 4", vf.makeInt(4), iv);
    }

    @Test
    public void sizeOfReallyBigListIsCorrect() {
        ImpValue iv = interpreter.interpret("begin x = [ ]; for i = 1 to 10000 do x = x + i; y = #x; end");
        assertNotNull(iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("y = #x", vf.makeInt(10000), env.getValue("y"));
    }

    @Test
    public void inForMembersIsTrue() {
        ImpValue iv = runExpression("1 in [2,1,4]");
        assertTrue("1 in [2,1,4]", iv.isTrue());
    }

    @Test
    public void inForNonMembersIsFalse() {
        ImpValue iv = runExpression("1 in [2,5,4]");
        assertTrue("1 in [2,5,4]", iv.isFalse());
    }

    @Test
    public void inForEmptyIsFalse() {
        ImpValue iv = runExpression("1 in []");
        assertTrue("1 in []", iv.isFalse());
    }

    @Test
    public void notInForMembersIsFalse() {
        ImpValue iv = runExpression("1 notin [2,1,4]");
        assertTrue("1 notin [2,1,4]", iv.isFalse());
    }

    @Test
    public void notInForNonMembersIsTrue() {
        ImpValue iv = runExpression("1 notin [2,5,4]");
        assertTrue("1 notin [2,5,4]", iv.isTrue());
    }

    @Test
    public void notInForEmptyIsTrue() {
        ImpValue iv = runExpression("1 notin []");
        assertTrue("1 notin []", iv.isTrue());
    }

    @Test
    public void insertAtFrontWorks() {
        ImpValue iv = runExpression("1 in 1 + [2,3]");
        assertTrue("1 in 1 + [2,3]", iv.isTrue());
    }

    @Test
    public void insertAtEndWorks() {
        ImpValue iv = runExpression("1 in [2,3] + 1");
        assertTrue("1 in [2,3] + 1", iv.isTrue());
    }

    @Test
    public void listAfterInsertAtFrontIsRightSize() {
        ImpValue iv = runExpression("#(1 + [2,3])");
        assertEquals("#(1 + [2,3]) is 3", vf.makeInt(3), iv);
    }

    @Test
    public void listAfterInsertAtEndIsRightSize() {
        ImpValue iv = runExpression("#([2,3]+1)");
        assertEquals("#([2,3]+1) is 3", vf.makeInt(3), iv);
    }

    @Test
    public void listAfterInsertOfDupsAtFrontIsRightSize() {
        ImpValue iv = runExpression("#(1 + [1,3])");
        assertEquals("#(1 + [1,3]) is 3", vf.makeInt(3), iv);
    }

    @Test
    public void listAfterInsertOfDupsAtEndIsRightSize() {
        ImpValue iv = runExpression("#([1,3]+1)");
        assertEquals("#([1,3]+1) is 3", vf.makeInt(3), iv);
    }

    @Test
    public void removeWorks() {
        ImpValue iv = runExpression("1 notin [1,2,3] - 1");
        assertTrue("1 notin [1,2,3] - 1", iv.isTrue());
    }

    @Test
    public void removeWorksWithDups() {
        ImpValue iv = runExpression("1 notin [1,2,1,3,1] - 1");
        assertTrue("1 notin [1,2,3] - 1", iv.isTrue());
    }

    @Test
    public void listAfterRemoveIsRightSize() {
        ImpValue iv = runExpression("#([1,2,3] - 1)");
        assertEquals("#([1,2,3] - 1) is 2", vf.makeInt(2), iv);
    }

    @Test
    public void listAfterRemoveWithDupsIsRightSize() {
        ImpValue iv = runExpression("#([1,2,1,3,1] - 1)");
        assertEquals("#([1,2,1,3,1] - 1) is 2", vf.makeInt(2), iv);
    }

    @Test
    public void removeOfNonMemberMaintainsList() {
        ImpValue iv = runExpression("#([1,2,3] - 4)");
        assertEquals("#([1,2,3] - 4) is 3", vf.makeInt(3), iv);
    }

    @Test(expected=ListIndexException.class)
    public void getWithNegativeIndexThrows() {
        runExpression("[1,2,3][-1]");
    }

    @Test(expected=ListIndexException.class)
    public void getWithZeroIndexThrows() {
        runExpression("[1,2,3][0]");
    }

    @Test(expected=ListIndexException.class)
    public void getWithEmptyListThrows() {
        runExpression("[][1]");
    }

    @Test
    public void getWithNormalListWorks() {
        interpreter.interpret("x = [1,5,3][2];");
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("x = [1,5,3][2] is 5", vf.makeInt(5), env.getValue("x"));
    }

    @Test
    public void toStringForEmptyList() {
        ImpValue iv = interpreter.interpret("x = [];");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with [", iv.toString().startsWith("["));
        assertTrue("toString result ends with ]", iv.toString().endsWith("]"));
    }

    @Test
    public void toStringForSingletonList() {
        ImpValue iv = interpreter.interpret("x = [99];");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with [", iv.toString().startsWith("["));
        assertTrue("toString result ends with ]", iv.toString().endsWith("]"));
        assertTrue("toString result contains 99", iv.toString().contains("99"));
    }

    @Test
    public void toStringForNormalList() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with [", iv.toString().startsWith("["));
        assertTrue("toString result ends with ]", iv.toString().endsWith("]"));
        assertTrue("toString result contains 10", iv.toString().contains("10"));
        assertTrue("toString result contains 30", iv.toString().contains("30"));
        assertTrue("toString result contains 99", iv.toString().contains("99"));
    }

    @Test
    public void listIsEqualToItself() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = runExpression("x == x", interpreter.getCurrentEnvironment());
        assertTrue("references to the same list are equal", iv.isTrue());
    }

    @Test
    public void listIsEqualForSame() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = interpreter.interpret("y = x;");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertTrue("identical lists through assignment are equal", iv.isTrue());
    }

    @Test
    public void listIsNotEqualForDistinct() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = interpreter.interpret("y = [10, 30, 99];");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertFalse("distinct lists with the same elements are not equal", iv.isTrue());
    }

    @Test
    public void listIsNotEqualForDifferent() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = interpreter.interpret("y = [10, 20, 99];");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertFalse("different lists are not equal", iv.isTrue());
    }

    @Test
    public void listIsReallyMutable() {
        ImpValue iv = interpreter.interpret("x = [10, 30, 99];");
        iv = interpreter.interpret("y = x + 101;");
        iv = runExpression("101 in x", interpreter.getCurrentEnvironment());
        assertTrue("x is really mutable", iv.isTrue());
    }
}
