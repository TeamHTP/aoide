package tech.aoide.audio;

import java.util.ArrayList;

public class AudioTrack {

    private ArrayList<AudioNode> nodes;

    public AudioTrack() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(AudioNode node) {
        this.nodes.add(node);
    }

}
