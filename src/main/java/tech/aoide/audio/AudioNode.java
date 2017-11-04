package tech.aoide.audio;

public class AudioNode {

    private String key;
    private double duration;
    private double speed;
    private double volume;
    private double offset;

    public AudioNode(String key, double duration, double speed, double volume, double offset) {
        this.key = key;
        this.duration = duration;
        this.speed = speed;
        this.volume = volume;
        this.offset = offset;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getOffset() {
        return this.offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "k:" + key + " d:" + duration + " s:" + speed + " v:" + volume + " o:" + offset;
    }
}
