package edu.ecu.cs.seng6245.imp.parser;

import java.io.IOException;
import java.io.StringReader;

import beaver.Parser;
import edu.ecu.cs.seng6245.imp.ast.Expression;
import edu.ecu.cs.seng6245.imp.ast.Statement;

public class ParserUtil {

    public static Statement parseStatement(String s) {
        ExpressionParser parser = new ExpressionParser();
        ExpressionScanner scanner = new ExpressionScanner(new StringReader(s));
        try {
            Statement stmt = (Statement) parser.parse(scanner);
            return stmt;
        } catch (Parser.Exception | IOException e) {
            System.err.println("Error, could not parse statement, make sure your syntax is correct: " + e.toString());
        }
        
        return null;
    }

    public static Expression parseExpression(String s) {
        ExpressionParser parser = new ExpressionParser();
        ExpressionScanner scanner = new ExpressionScanner(new StringReader(s));
        try {
            Expression expr = (Expression) parser.parse(scanner,ExpressionParser.AltGoals.expr);
            return expr;
        } catch (Parser.Exception | IOException e) {
            System.err.println("Error, could not parse expression, make sure your syntax is correct: " + e.toString());
        }
        
        return null;
    }

}
