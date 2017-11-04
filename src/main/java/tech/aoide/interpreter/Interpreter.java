package tech.aoide.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import tech.aoide.audio.AudioNode;
import tech.aoide.audio.AudioTrack;
import tech.aoide.parser.Java8Lexer;
import tech.aoide.parser.Java8Parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\domin\\Documents\\code\\antlr\\java8\\examples\\HelloWorld.java");
        byte[] encoded = Files.readAllBytes(file.toPath());
        String code = new String(encoded, Charset.forName("utf-8"));
        Interpreter interpreter = new Interpreter(code);
        ArrayList<AudioTrack> tracks = interpreter.interpret();
        for (AudioTrack track : tracks) {
            for (AudioNode node : track.getNodes()) {
                System.out.print(node + ", ");
            }
            System.out.println();
        }
    }

}
