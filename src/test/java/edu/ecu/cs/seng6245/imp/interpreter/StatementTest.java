package edu.ecu.cs.seng6245.imp.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

public class StatementTest {
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
    public void testAssign() {
        String pgm = "x = 5;";
        ImpValue iv = interpreter.interpret(pgm);
        Environment env = interpreter.getCurrentEnvironment();
        assertNotNull(iv);
        assertEquals("assign yields void", vf.makeVoid(), iv);        
        assertEquals("x==5", vf.makeInt(5), env.getValue("x"));
    }

    @Test
    public void testAssignTwice() {
        String pgm = "begin x = 5; x = 10; end";
        ImpValue iv = interpreter.interpret(pgm);
        Environment env = interpreter.getCurrentEnvironment();
        assertNotNull(iv);
        assertEquals("assign yields void", vf.makeVoid(), iv);        
        assertEquals("x==10", vf.makeInt(10), env.getValue("x"));
    }

    @Test
    public void testAssignAndAdd() {
        String pgm = "begin x = 5; y = 10; z = x + y; end";
        ImpValue iv = interpreter.interpret(pgm);
        Environment env = interpreter.getCurrentEnvironment();
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);        
        assertEquals("x==5", vf.makeInt(5), env.getValue("x"));
        assertEquals("y==10", vf.makeInt(10), env.getValue("y"));
        assertEquals("z==15", vf.makeInt(15), env.getValue("z"));
    }
    
    @Test
    public void testSingletonBlock() {
        String pgm = "begin n = 1; end";
        ImpValue iv = interpreter.interpret(pgm);
        Environment env = interpreter.getCurrentEnvironment();
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        assertEquals("n==1", vf.makeInt(1), env.getValue("n"));
    }

    @Test
    public void testLongerBlock() {
        String pgm = "begin n = 1; n = 2; n = 3; n = 4; n = 5; n = 6; n = 7; end";
        ImpValue iv = interpreter.interpret(pgm);
        Environment env = interpreter.getCurrentEnvironment();
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        assertEquals("n==7", vf.makeInt(7), env.getValue("n"));
    }
    
    @Test
    public void testEmptyFor() {
        String pgm = "for i = 1 to 10 do 3;";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("for yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of i should be 10", vf.makeInt(10), env.getValue("i"));
        
    }

    @Test
    public void testSimpleFor() {
        String pgm = "begin n = 0; for i = 1 to 10 do n = n + i; end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of i should be 10", vf.makeInt(10), env.getValue("i"));
        assertEquals("value of n should be 55", vf.makeInt(55), env.getValue("n"));
        
    }
    
    @Test
    public void testForThatDoesNotLoop() {
        String pgm = "begin n = 0; for i = 11 to 10 do n = n + i; end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of i should be 11", vf.makeInt(11), env.getValue("i"));
        assertEquals("value of n should be 0", vf.makeInt(0), env.getValue("n"));
    }
    
    @Test
    public void testEmptyWhile() {
        String pgm = "while (1 < 0) 5;";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("while yields void", vf.makeVoid(), iv);
    }

    @Test
    public void testWhileSingleIteration() {
        String pgm = "begin f = 1; n = 2; while (n > 1) begin f = f * n; n = n - 1; end end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of f is 2", vf.makeInt(2), env.getValue("f"));
    }

    @Test
    public void testWhileMultiIteration() {
        String pgm = "begin f = 1; n = 5; while (n > 0) begin f = f * n; n = n - 1; end end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of f is 120", vf.makeInt(120), env.getValue("f"));
    }

    @Test
    public void testWhileNoIteration() {
        String pgm = "begin f = 1; n = 0; while (n > 0) begin f = f * n; n = n - 1; end end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of f is 1", vf.makeInt(1), env.getValue("f"));
    }

    @Test
    public void testEmptyIf() {
        String pgm = "if (1 > 2) then 4; else 10;";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("if yields void", vf.makeVoid(), iv);
    }

    @Test
    public void testIfWhenTrue() {
        String pgm = "begin i = 0; if (i > 2) then i = 4; else i = 10; end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of i is 10", vf.makeInt(10), env.getValue("i"));
    }

    @Test
    public void testIfWhenFalse() {
        String pgm = "begin i = 0; if (i < 2) then i = 4; else i = 10; end";
        ImpValue iv = interpreter.interpret(pgm);
        assertNotNull(iv);
        assertEquals("block yields void", vf.makeVoid(), iv);
        Environment env = interpreter.getCurrentEnvironment();
        assertEquals("value of i is 4", vf.makeInt(4), env.getValue("i"));
    }

}
