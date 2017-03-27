package edu.ecu.cs.seng6245.imp.ast;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * Represents a print statement, which prints the result of the given
 * expression on the console.
 * 
 * @author Mark Hills
 * @version 1.0
 *
 */
public class PrintStatement extends Statement {
	/** Expression to print */
	private final Expression printExpression;
	
	/**
	 * Create a new print statement AST node.
	 * 
	 * @param a the expression to be printed
	 */
	public PrintStatement(Expression a) {
		printExpression = a;
	}

	/**
	 * Run the print statement, which will evaluate the expression,
	 * print the result of this evaluation, and then return void.
	 * 
	 * @param env the current environment
	 * 
	 * @return void
	 */
	@Override
	public ImpValue interpret(Environment env) {
		ImpValue iv = printExpression.interpret(env);
		System.out.println("### " + iv.toString());
		return ImpValueFactory.getValueFactory().makeVoid();
	}
}
