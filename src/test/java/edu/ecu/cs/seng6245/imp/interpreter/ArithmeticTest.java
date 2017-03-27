package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ecu.cs.seng6245.imp.exceptions.NameNotDefinedException;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class ArithmeticTest extends ImpTest {
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
    public void testMultiply() {
        ImpValue iv = runExpression("1*2");
        assertNotNull(iv);
        assertEquals("1*2", vf.makeInt(2), iv);
    }    

    @Test
    public void testDivide() {
        ImpValue iv = runExpression("4/2");
        assertNotNull(iv);
        assertEquals("4/2", vf.makeInt(2), iv);
    }    

    @Test(expected = ArithmeticException.class)
    public void testDivideBy0() {
        runExpression("4/0");
    }    

    @Test
    public void testPlus() {
        ImpValue iv = runExpression("1+2");
        assertNotNull(iv);
        assertEquals("1+2", vf.makeInt(3), iv);
    }    

    @Test
    public void testMinus() {
        ImpValue iv = runExpression("1-2");
        assertNotNull(iv);
        assertEquals("1-2", vf.makeInt(-1), iv);
    }    

    @Test
    public void testUnaryMinus() {
        ImpValue iv = runExpression("-2");
        assertNotNull(iv);
        assertEquals("-2", vf.makeInt(-2), iv);
    }
    
    @Test
    public void testPlusWithVariables() {
        Environment env = EasyMock.createMock(Environment.class);
        EasyMock.expect(env.getValue("x")).andReturn(vf.makeInt(4)).times(2);
        EasyMock.replay(env);
        ImpValue iv = runExpression("x+x", env);
        assertNotNull(iv);
        assertEquals("x+x", vf.makeInt(8), iv);
    }
    
    @Test
    public void testMultiplyWithVariables() {
        Environment env = EasyMock.createMock(Environment.class);
        EasyMock.expect(env.getValue("x")).andReturn(vf.makeInt(2)).times(3);
        EasyMock.expect(env.getValue("y")).andReturn(vf.makeInt(4));
        EasyMock.expect(env.getValue("z")).andReturn(vf.makeInt(5));
        EasyMock.replay(env);
        
        String toTest = "x*x*y*z*x"; // 160, pass this to the interpreter...
        ImpValue iv = runExpression(toTest, env);
        assertNotNull(iv);
        assertEquals(toTest, vf.makeInt(160), iv);
    }
    
    @Test(expected=NameNotDefinedException.class)
    public void testPlusWithUndefinedVariable() {
        interpreter.interpret("3+x;");
    }

    @Test(expected=NameNotDefinedException.class)
    public void testTimesWithUndefinedVariable() {
        interpreter.interpret("3*x;");
    }
}
