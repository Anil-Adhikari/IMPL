package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class RelationalTest extends ImpTest {
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
    public void testEqualsWhenEqual() {
        ImpValue iv = runExpression("1 == 1");
        assertNotNull(iv);
        assertTrue("1 == 1 should be true", iv.isTrue());
    }

    @Test
    public void testEqualsWhenNotEqual() {
        ImpValue iv = runExpression("1 == 2");
        assertNotNull(iv);
        assertTrue("1 == 2 should be false", iv.isFalse());
    }

    @Test
    public void testEqualsWithIdentifiers() {
        interpreter.interpret("begin x = 1; y = 1; x == y; end");
        Environment env = interpreter.getCurrentEnvironment();
        ImpValue iv = runExpression("x == y", env);
        assertNotNull(iv);
        assertEquals("x == 1", vf.makeInt(1), env.getValue("x"));
        assertEquals("y == 1", vf.makeInt(1), env.getValue("y"));
        assertTrue("x == y should be true when both are set to 1", iv.isTrue());
    }

    @Test
    public void testNotEqualsWhenNotEqual() {
        ImpValue iv = runExpression("1 != 2");
        assertNotNull(iv);
        assertTrue("1 != 2 should be true", iv.isTrue());
    }

    @Test
    public void testNotEqualsWhenEqual() {
        ImpValue iv = runExpression("1 != 1");
        assertNotNull(iv);
        assertTrue("1 != 1 should be false", iv.isFalse());
    }

    @Test
    public void testLessThanWhenLessThan() {
        ImpValue iv = runExpression("15 < 241");
        assertNotNull(iv);
        assertTrue("15 < 241 should be true", iv.isTrue());
    }

    @Test
    public void testLessThanWhenEqual() {
        ImpValue iv = runExpression("5 < 5");
        assertNotNull(iv);
        assertTrue("5 < 5 should be false", iv.isFalse());
}

    @Test
    public void testLessThanWhenGreaterThan() {
        ImpValue iv = runExpression("51 < 3");
        assertNotNull(iv);
        assertTrue("51 < 3 should be false", iv.isFalse());
    }

    @Test
    public void testLessThanOrEqualWhenLessThan() {
        ImpValue iv = runExpression("12 <= 25");
        assertNotNull(iv);
        assertTrue("12 <= 25 should be true", iv.isTrue());
    }

    @Test
    public void testLessThanOrEqualWhenEqual() {
        ImpValue iv = runExpression("12 <= 12");
        assertNotNull(iv);
        assertTrue("12 <= 12 should be true", iv.isTrue());
    }

    @Test
    public void testLessThanOrEqualWhenGreaterThan() {
        ImpValue iv = runExpression("153 <= 2");
        assertNotNull(iv);
        assertTrue("153 <= 2 should be false", iv.isFalse());
    }
    
    @Test
    public void testGreaterThanWhenLessThan() {
        ImpValue iv = runExpression("15 > 20");
        assertNotNull(iv);
        assertTrue("15 > 20 should be false", iv.isFalse());
    }

    @Test
    public void testGreaterThanWhenEqual() {
        ImpValue iv = runExpression("18 > 18");
        assertNotNull(iv);
        assertTrue("18 > 18 should be false", iv.isFalse());
    }

    @Test
    public void testGreaterThanWhenGreaterThan() {
        ImpValue iv = runExpression("124 > 3");
        assertNotNull(iv);
        assertTrue("124 > 3 should be true", iv.isTrue());
    }
    
    @Test
    public void testGreaterThanOrEqualWhenLessThan() {
        ImpValue iv = runExpression("15 >= 25");
        assertNotNull(iv);
        assertTrue("15 >= 25 should be false", iv.isFalse());
    }

    @Test
    public void testGreaterThanOrEqualWhenEqual() {
        ImpValue iv = runExpression("100 >= 100");
        assertNotNull(iv);
        assertTrue("100 >= 100 should be true", iv.isTrue());
    }

    @Test
    public void testGreaterThanOrEqualWhenGreaterThan() {
        ImpValue iv = runExpression("153 >= 29");
        assertNotNull(iv);
        assertTrue("153 >= 29 should be true", iv.isTrue());
    }
    
}
