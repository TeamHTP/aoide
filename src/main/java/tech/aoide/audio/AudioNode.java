package tech.aoide.audio;

public class AudioNode {

    private String key;
    private String wave;
    private double duration;

    public AudioNode(String key, String wave, double duration) {
        this.key = key;
        this.wave = wave;
        this.duration = duration;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWave() {
        return this.wave;
    }

    public void setWave(String wave) { this.wave = wave; }

    public double getDuration() { return this.duration; }

    public void setDuration(double duration) { this.duration = duration; }


    @Override
    public String toString() {
        return "k:" + this.key + "w:" + this.wave + " d:" + this.duration + " s:";
    }
}
