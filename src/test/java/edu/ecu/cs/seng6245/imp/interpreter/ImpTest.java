package edu.ecu.cs.seng6245.imp.interpreter;

import edu.ecu.cs.seng6245.imp.ast.Expression;
import edu.ecu.cs.seng6245.imp.parser.ParserUtil;
import edu.ecu.cs.seng6245.imp.value.ImpValue;

public class ImpTest {
    protected ImpValue runExpression(String s) {
        return runExpression(s, new Environment());
    }
    
    protected ImpValue runExpression(String s, Environment env) {
        Expression expr = ParserUtil.parseExpression(s);
        if (expr != null) {
            return expr.interpret(env);
        } else {
            return null;
        }
    }
}
