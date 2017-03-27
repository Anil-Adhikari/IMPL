package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ecu.cs.seng6245.imp.exceptions.EmptySetException;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class SetTest extends ImpTest {
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
    public void sizeOfEmptySetIsZero() {
        ImpValue iv = runExpression("#{}");
        assertNotNull(iv);
        assertEquals("#{} is 0", vf.makeInt(0), iv);
    }

    @Test
    public void sizeOfSingletonSetIsOne() {
        ImpValue iv = runExpression("#{ 1 }");
        assertNotNull(iv);
        assertEquals("#{ 1 } is 1", vf.makeInt(1), iv);
    }

    @Test
    public void sizeOfBiggerSetIsCorrect() {
        ImpValue iv = runExpression("#{ 1, 2, 3 }");
        assertNotNull(iv);
        assertEquals("#{ 1, 2, 3 } is 3", vf.makeInt(3), iv);
    }

    @Test
    public void dupsAreIgnored() {
        ImpValue iv = runExpression("#{ 1, 2, 2, 3 }");
        assertNotNull(iv);
        assertEquals("#{ 1, 2, 2, 3 } is 3", vf.makeInt(3), iv);
    }

    @Test
    public void sizeOfReallyBigSetIsCorrect() {
        ImpValue iv = interpreter.interpret("begin x = { }; for i = 1 to 10000 do x = x + i; y = #x; end");
        assertNotNull(iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("y = #x", vf.makeInt(10000), env.getValue("y"));
    }

    @Test
    public void inForMembersIsTrue() {
        ImpValue iv = runExpression("1 in {2,1,4}");
        assertTrue("1 in {2,1,4}", iv.isTrue());
    }

    @Test
    public void inForNonMembersIsFalse() {
        ImpValue iv = runExpression("1 in {2,5,4}");
        assertTrue("1 in {2,5,4}", iv.isFalse());
    }

    @Test
    public void inForEmptyIsFalse() {
        ImpValue iv = runExpression("1 in {}");
        assertTrue("1 in {}", iv.isFalse());
    }

    @Test
    public void notInForMembersIsFalse() {
        ImpValue iv = runExpression("1 notin {2,1,4}");
        assertTrue("1 notin {2,1,4}", iv.isFalse());
    }

    @Test
    public void notInForNonMembersIsTrue() {
        ImpValue iv = runExpression("1 notin {2,5,4}");
        assertTrue("1 notin {2,5,4}", iv.isTrue());
    }

    @Test
    public void notInForEmptyIsTrue() {
        ImpValue iv = runExpression("1 notin {}");
        assertTrue("1 notin {}", iv.isTrue());
    }

    @Test
    public void insertWorks() {
        ImpValue iv = runExpression("1 in 1 + {2,3}");
        assertTrue("1 in 1 + {2,3}", iv.isTrue());
    }

    @Test
    public void setAfterInsertIsRightSize() {
        ImpValue iv = runExpression("#(1 + {2,3})");
        assertEquals("#(1 + {2,3}) is 3", vf.makeInt(3), iv);
    }

    @Test
    public void setAfterInsertOfDupsIsRightSize() {
        ImpValue iv = runExpression("#(1 + {1,3})");
        assertEquals("#(1 + {1,3}) is 2", vf.makeInt(2), iv);
    }

    @Test
    public void removeWorks() {
        ImpValue iv = runExpression("1 notin {1,2,3} - 1");
        assertTrue("1 notin {1,2,3} - 1", iv.isTrue());
    }

    @Test
    public void setAfterRemoveIsRightSize() {
        ImpValue iv = runExpression("#({1,2,3} - 1)");
        assertEquals("#({1,2,3} - 1) is 2", vf.makeInt(2), iv);
    }

    @Test
    public void removeOfNonMemberMaintainsSet() {
        ImpValue iv = runExpression("#({1,2,3} - 4)");
        assertEquals("#({1,2,3} - 4) is 3", vf.makeInt(3), iv);
    }

    @Test(expected=EmptySetException.class)
    public void selectFromEmptySetThrows() {
        runExpression("!{}");
    }

    @Test
    public void selectFromSingletonSetReturnsElement() {
        ImpValue iv = runExpression("!{72}");
        assertEquals("!{72} is 72", vf.makeInt(72), iv);
    }

    @Test
    public void selectFromBiggerSetInSet() {
        interpreter.interpret("begin x = { }; for i = 1 to 100 do x = x + i; y = !x; end");
        ImpValue iv = runExpression("y in x", interpreter.getCurrentEnvironment());
        assertTrue("!x in x", iv.isTrue());
    }

    @Test
    public void toStringForEmptySet() {
        ImpValue iv = interpreter.interpret("x = {};");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with {", iv.toString().startsWith("{"));
        assertTrue("toString result ends with }", iv.toString().endsWith("}"));
    }

    @Test
    public void toStringForSingletonSet() {
        ImpValue iv = interpreter.interpret("x = {99};");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with {", iv.toString().startsWith("{"));
        assertTrue("toString result ends with }", iv.toString().endsWith("}"));
        assertTrue("toString result contains 99", iv.toString().contains("99"));
    }

    @Test
    public void toStringForNormalSet() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = runExpression("x", interpreter.getCurrentEnvironment());
        assertTrue("toString result starts with {", iv.toString().startsWith("{"));
        assertTrue("toString result ends with }", iv.toString().endsWith("}"));
        assertTrue("toString result contains 10", iv.toString().contains("10"));
        assertTrue("toString result contains 30", iv.toString().contains("30"));
        assertTrue("toString result contains 99", iv.toString().contains("99"));
    }

    @Test
    public void setIsEqualToItself() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = runExpression("x == x", interpreter.getCurrentEnvironment());
        assertTrue("references to the same set are equal", iv.isTrue());
    }

    @Test
    public void setIsEqualForSame() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = interpreter.interpret("y = x;");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertTrue("identical sets through assignment are equal", iv.isTrue());
    }

    @Test
    public void setIsEqualForDistinct() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = interpreter.interpret("y = {10, 30, 99};");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertTrue("distinct sets with the same elements are equal", iv.isTrue());
    }

    @Test
    public void setIsNotEqualForDifferent() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = interpreter.interpret("y = {10, 20, 99};");
        iv = runExpression("x == y", interpreter.getCurrentEnvironment());
        assertFalse("different sets are not equal", iv.isTrue());
    }

    @Test
    public void setReallyIsImmutable() {
        ImpValue iv = interpreter.interpret("x = {10, 30, 99};");
        iv = interpreter.interpret("y = x + 105;");
        iv = runExpression("x == {10,30,99}", interpreter.getCurrentEnvironment());
        assertTrue("x is really immutable", iv.isTrue());
    }

}
