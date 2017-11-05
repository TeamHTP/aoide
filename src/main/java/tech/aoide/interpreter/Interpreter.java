package tech.aoide.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import tech.aoide.audio.AudioNode;
import tech.aoide.audio.AudioTrack;
import tech.aoide.music.Chord;
import tech.aoide.music.Key;
import tech.aoide.music.Wave;
import tech.aoide.parser.*;

import java.util.ArrayList;

public class Interpreter {

    private String code;
    private ParseTree tree;
    private ArrayList<AudioTrack> chords = new ArrayList<>();
    private Key key;

    public Interpreter(String code, String language) {
        this.code = code;
        Lexer lexer;
        CommonTokenStream tokens;
        Parser parser;
        switch (language) {
            case "Java8":
                lexer = new Java8Lexer(new ANTLRInputStream(code));
                tokens = new CommonTokenStream(lexer);
                parser = new Java8Parser(tokens);
                tree = ((Java8Parser)parser).compilationUnit();
                break;
            case "Python3":
                lexer = new Python3Lexer(new ANTLRInputStream(code));
                tokens = new CommonTokenStream(lexer);
                parser = new Python3Parser(tokens);
                tree = ((Python3Parser)parser).file_input();
                break;
            default:
                throw new IllegalArgumentException("Language not supported");
        }
        key = getKey();
    }

    private void traverse(ParseTree tree, Chord traverseChord, Wave wave) {
        AudioTrack temp = new AudioTrack();
        int count = 0;
        for (int i=0;i<tree.getChildCount();i++) {
            String terminalVal = getTerminal(tree.getChild(i));
            if (terminalVal != null) {
//                if (terminalVal.equals("if") || terminalVal.equals("for") || terminalVal.equals("while")) {
//                    wave = Wave.values()[(wave.ordinal() + 1) % Wave.values().length];
//                }
                temp.addNode(new AudioNode(key.getNote((traverseChord.ordinal() + count) % 7) + (4 + (traverseChord.ordinal() + count) / 7), wave.name().toLowerCase(), Math.max(3, Math.min(terminalVal.length(), 12) / 2)));
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
        return tree.getText();
    }

    private Key getKey() {
        return Key.values()[code.length() % Key.values().length];
    }

    public ArrayList<AudioTrack> interpret() {
        System.gc();
        for (int i = 0; i < tree.getChildCount(); i++) {
            if (tree.getChild(i).getPayload() instanceof Java8Parser.TypeDeclarationContext) {
                traverse(tree.getChild(i), Chord.I, Wave.SINE);
            }
        }
        System.gc();
        return chords;
    }

}
