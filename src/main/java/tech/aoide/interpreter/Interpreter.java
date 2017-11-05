package tech.aoide.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import tech.aoide.audio.AudioNode;
import tech.aoide.audio.AudioTrack;
import tech.aoide.music.Chord;
import tech.aoide.music.Key;
import tech.aoide.music.Wave;
import tech.aoide.parser.Java8Lexer;
import tech.aoide.parser.Java8Parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter {

    private static final char[] EXCLUDE = new char[] { ';', '{', '}', '(', ')', '[', ']' };

    private String code;
    private Java8Lexer lexer;
    private CommonTokenStream tokens;
    private Java8Parser parser;
    private Java8Parser.CompilationUnitContext tree;
    private int imports;
    private Listener listener;
    private ArrayList<AudioTrack> chords = new ArrayList<>();
    private Key key;

    public Interpreter(String code) {
        this.code = code;
        lexer = new Java8Lexer(new ANTLRInputStream(code));
        tokens = new CommonTokenStream(lexer);
        parser = new Java8Parser(tokens);
        tree = parser.compilationUnit();
        countImports();
        key = getKey();
        for (ParseTree child : tree.children) {
            if (child.getPayload() instanceof Java8Parser.TypeDeclarationContext) {
                traverse(child.getChild(0).getChild(0), Chord.I);
            }
        }
        System.out.println(Arrays.toString(chords.toArray()));
    }

    private void countImports() {
        imports = 0;
        for (ParseTree child : tree.children) {
            if (child instanceof Java8Parser.ImportDeclarationContext) {
                imports++;
            }
        }
    }

    private void traverse(ParseTree tree, Chord traverseChord, Wave wave) {
        AudioTrack temp = new AudioTrack();
        int count = 0;
        for (int i=0;i<tree.getChildCount();i++) {
            String terminalVal = getTerminal(tree.getChild(i));
            if (terminalVal != null) {
                if (terminalVal.equals("if")) {
                    wave = Wave.values()[(wave.ordinal() + 1) % Wave.values().length];
                }
                temp.addNode(new AudioNode(key.getNote((traverseChord.ordinal() + count) % 7) + "4", wave.name().toLowerCase(), 3));
                count += 2;
            }
            else {
                if (temp.getNodes().size() != 0) {
                    traverseChord = Chord.getProgressions(traverseChord)[temp.getNodes().get(0).getKey().getBytes()[0] % Chord.getProgressions(traverseChord).length];
                }
                traverse(tree.getChild(i), traverseChord, wave);
            }
        }
        if (temp.getNodes().size() != 0) {
            chords.add(temp);
        }
    }

    private String getTerminal(ParseTree tree) {
        if (tree.getChildCount() > 1) {
            return null;
        }
        if (tree.getChildCount() == 1) {
            return getTerminal(tree.getChild(0));
        }
        for (char c : EXCLUDE) {
            if (tree.getText().charAt(0) == c) {
                return null;
            }
        }
        return tree.getText();
    }

    private Key getKey() {
        return Key.values()[imports % Key.values().length];
    }

    public ArrayList<AudioTrack> interpret() {
        return chords;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\domin\\Documents\\code\\antlr\\java8\\examples\\HelloWorld.java");
        byte[] encoded = Files.readAllBytes(file.toPath());
        String code = new String(encoded, Charset.forName("utf-8"));
        Interpreter interpreter = new Interpreter(code);
        System.out.println(interpreter.imports);
    }

}
