package tech.aoide.audio;

import java.util.ArrayList;
import java.util.Arrays;

public class AudioTrack {

    private ArrayList<AudioNode> nodes;

    private int codeStart;
    private int codeEnd;

    public AudioTrack() {
        this.nodes = new ArrayList<>();
        this.codeStart = 0;
        this.codeEnd = 0;
    }

    public void addNode(AudioNode node) {
        this.nodes.add(node);
    }

    public ArrayList<AudioNode> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes.toArray());
    }

    public int getCodeStart() {
        return this.codeStart;
    }

    public void setCodeStart(int codeStart) {
        this.codeStart = codeStart;
    }

    public int getCodeEnd() {
        return this.codeEnd;
    }

    public void setCodeEnd(int codeEnd) {
        this.codeEnd = codeEnd;
    }
}
