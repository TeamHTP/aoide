package tech.aoide.music;

public enum Key {
    G_MAJOR(new String[] { "G", "A", "B", "C", "D", "E", "F#" }),
    D_MAJOR(new String[] { "D", "E", "F#", "G", "A", "B", "C#"}),
    A_MAJOR(new String[] { "A", "B", "C#", "D", "E", "F#", "G#"}),
    E_MAJOR(new String[] { "E", "F#", "G#", "A", "B", "C#", "D#"}),
    B_MAJOR(new String[] { "B", "C#", "D#", "E", "F#", "G#", "A#"}),
    FS_MAJOR(new String[] { "F#", "G#", "A#", "B", "C#", "D#", "E#"}),
    CS_MAJOR(new String[] { "C#", "D#", "E#", "F#", "G#", "A#", "B#"}),
    Cb_MAJOR(new String[] { "Cb", "Db", "Eb", "Fb", "Gb", "Ab", "Bb"}),
    Gb_MAJOR(new String[] { "Gb", "Ab", "Bb", "Cb", "Db", "Eb", "F"}),
    Db_MAJOR(new String[] { "Db", "Eb", "F", "Gb", "Ab", "Bb", "C"}),
    Ab_MAJOR(new String[] { "Ab", "Bb", "C", "Db", "Eb", "F", "G"}),
    Eb_MAJOR(new String[] { "Eb", "F", "G", "Ab", "Bb", "C", "D"}),
    Bb_MAJOR(new String[] { "Bb", "C", "D", "Eb", "F", "G", "A"}),
    F_MAJOR(new String[] { "F", "G", "A", "B#", "C", "D", "E"}),
    C_MAJOR(new String[] { "C", "D", "E", "F", "G", "A", "B"});

    private String[] notes;

    Key(String[] notes) {
        this.notes = notes;
    }

    public String[] getNotes() {
        return notes;
    }

    public String getNote(int index) {
        return notes[index];
    }
}
