package pl.wojtasik.adrian.java.basic.note.dao;

public class NoteFiltering {
    private int start;
    private int offset;

    public NoteFiltering(int start, int offset) {
        this.start = start;
        this.offset = offset;
    }

    public int getStart() {
        return start;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "NoteFiltering{" +
                "start=" + start +
                ", offset=" + offset +
                '}';
    }
}
