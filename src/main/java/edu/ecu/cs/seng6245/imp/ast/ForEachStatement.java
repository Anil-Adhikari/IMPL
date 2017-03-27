package edu.ecu.cs.seng6245.imp.ast;

import java.util.Iterator;

import edu.ecu.cs.seng6245.imp.interpreter.Environment;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import edu.ecu.cs.seng6245.imp.value.ImpValueFactory;

/**
 * IMP class representing foreach statements.
 * 
 * @author Mark Hills
 * @version 1.0
 *
 */
public class ForEachStatement extends Statement {
	/** The foreach identifier */
	private final String ident;
	
	/** The foreach generator expression */
	private final Expression gen;
	
	/** The name of the iterator we want to use */
	private final String iterIdent;
	
	/** The body of the foreach */
	private final Statement body;
	
	public ForEachStatement(String i, Expression a, String j, Statement b) {
		ident = i;
		gen = a;
		iterIdent = (j.trim().length()==0) ? "standard" : j;
		body = b;
	}

	@Override
	public ImpValue interpret(Environment env) {
		ImpValueFactory vf = ImpValueFactory.getValueFactory();
		ImpValue iv = gen.interpret(env);
		Iterator<ImpValue> iter = iv.getNamedIterator(iterIdent);
		while (iter.hasNext()) {
			env.setValue(ident, iter.next());
			body.interpret(env);
		}
		return vf.makeVoid();
	}

}
