package tech.aoide.audio;

import java.util.ArrayList;
import java.util.Arrays;

public class AudioTrack {

    private ArrayList<AudioNode> nodes;

    public AudioTrack() {
        this.nodes = new ArrayList<>();
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

}
