package tech.aoide.vm.jsinterpreter;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import tech.aoide.audio.AudioNode;
import tech.aoide.audio.AudioTrack;
import tech.aoide.music.Chord;
import tech.aoide.music.Key;
import tech.aoide.music.Wave;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class JSVM {

    private ScriptEngineManager engineManager;
    private ScriptEngine engine;

    public JSVM() {
    }

    public ArrayList<AudioTrack> eval(String source) throws ScriptException {
        this.engineManager = new ScriptEngineManager();
        this.engine = engineManager.getEngineByName("nashorn");
        ArrayList<AudioTrack> chords = new ArrayList<>();
        Chord chord = Chord.I;
        try {
            Key key = Key.values()[source.length() % Key.values().length];
            engine.put("source", source);
            engine.eval(new FileReader("acorn_interpreter.js"));
            engine.eval("var interp = new Interpreter(source);");
            while (engine.eval("interp.step();").equals(true)) {
                JSObject stateStack = (JSObject) ((JSObject)engine.get("interp")).getMember("stateStack");
                Number[] ints = new Number[2];
                ints[0] = (Number) ((ScriptObjectMirror) ((JSObject)stateStack.values().toArray()[stateStack.values().toArray().length - 1]).getMember("node")).getMember("start");
                ints[1] = (Number) ((ScriptObjectMirror) ((JSObject)stateStack.values().toArray()[stateStack.values().toArray().length - 1]).getMember("node")).getMember("end");
                //System.out.println();
                String code = source.substring(ints[0].intValue(), ints[1].intValue());
                AudioTrack track = new AudioTrack();
                track.setCodeStart(ints[0].intValue());
                track.setCodeEnd(ints[1].intValue());
                for (int i = 0; i < Math.min(6, code.length()); i++) {
                    int offset = 0;
                    switch (i) {
                        case 0:
                            offset = 0;
                            break;
                        case 1:
                            offset = 2;
                            break;
                        case 2:
                            offset = 4;
                            break;
                        case 3:
                            offset = 7;
                            break;
                        case 4:
                            offset = 9;
                            break;
                        case 5:
                            offset = 11;
                            break;
                    }
                    Wave wave = Wave.SINE;
                    if (code.contains("(") || code.contains(")")) {
                        wave = Wave.SINE;
                    }
                    else if (code.contains(">") || code.contains("<")) {
                        wave = Wave.SAWTOOTH;
                    }
                    else if (code.contains("[") || code.contains("]")) {
                        wave = Wave.SQUARE;
                    }
                    else if (code.contains(".")) {
                        wave = Wave.TRIANGLE;
                    }
                    track.addNode(new AudioNode(key.getNote((chord.ordinal() + offset) % 7) + (Math.max(2, code.getBytes()[0] % 6) + (chord.ordinal() + offset) / 7), wave.name().toLowerCase(), 3));
                }
                chord = Chord.getProgressions(chord)[code.length() % Chord.getProgressions(chord).length];
                chords.add(track);
            }
            return chords;
        } catch (ScriptException e) {
            throw e;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
