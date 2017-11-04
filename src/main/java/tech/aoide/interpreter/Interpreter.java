package tech.aoide.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import tech.aoide.audio.AudioTrack;
import tech.aoide.parser.Java8Lexer;
import tech.aoide.parser.Java8Parser;

import java.util.ArrayList;

public class Interpreter {

    private String code;
    private Java8Lexer lexer;
    private CommonTokenStream tokens;
    private Java8Parser parser;
    private Java8Parser.CompilationUnitContext tree;
    private Listener listener;

    public Interpreter(String code) {
        this.code = code;
        lexer = new Java8Lexer(new ANTLRInputStream(code));
        tokens = new CommonTokenStream(lexer);
        parser = new Java8Parser(tokens);
        tree = parser.compilationUnit();
        listener = new Listener();
    }

    public ArrayList<AudioTrack> interpret() {
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        return listener.getTracks();
    }

}
