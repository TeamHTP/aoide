package tech.aoide.music;

import java.util.HashMap;
import java.util.Map;

public enum Chord {
    I,
    II,
    III,
    IV,
    V,
    VI,
    VII;

    private static Map<Chord, Chord[]> progressions = new HashMap<>();

    static {
        progressions.put(I, new Chord[] { III, VI, IV, VII, V });
        progressions.put(II, new Chord[] { V });
        progressions.put(III, new Chord[] { IV, VI });
        progressions.put(IV, new Chord[] { I, II, VII });
        progressions.put(V, new Chord[] { I, VI });
        progressions.put(VI, new Chord[] { IV });
        progressions.put(VII, new Chord[] { I });
    }

    public static Chord[] getProgressions(Chord chord) {
        return progressions.get(chord);
    }

}
