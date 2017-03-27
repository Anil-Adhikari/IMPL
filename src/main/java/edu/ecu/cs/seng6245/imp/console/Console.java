package edu.ecu.cs.seng6245.imp.console;

import java.io.IOException;

import edu.ecu.cs.seng6245.imp.interpreter.Interpreter;
import edu.ecu.cs.seng6245.imp.value.ImpValue;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Console {

    private final Interpreter interpreter;

    public Console() {
        interpreter = new Interpreter();
    }

    public void repl() {

        try {
            Terminal terminal = TerminalBuilder.terminal();
            terminal.writer().println("Welcome to the IMP console!");
            terminal.writer().println("Type each command on its own line, type #quit to exit and #reset to reset the environment.");
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
            LineReaderImpl specificReader = reader instanceof LineReaderImpl ? (LineReaderImpl) reader : null;

            while(true) {
                String contents = reader.readLine("IMP>");
                contents = contents.trim();
                if (contents.equalsIgnoreCase("#quit")) {
                    break;
                } else if (contents.equalsIgnoreCase("#reset")) {
                    interpreter.resetInterpreter();
                    continue;
                } else {
                    if (specificReader != null) {
                        int peekChar = specificReader.peekCharacter(100);
                        if (Character.isWhitespace(peekChar)) {
                            specificReader.readCharacter(); // If we have a stray whitespace character left, discard it
                        }
                    }
                    if (contents.length() == 0) {
                        continue;
                    }
                }
                ImpValue iv = interpreter.interpret(contents);
                terminal.writer().println("result: " + iv);
            }

            terminal.writer().println("Exiting...");
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Console c = new Console();
        c.repl();
    }
}
