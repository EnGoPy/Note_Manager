package pl.wojtasik.adrian.java.basic.note.exception;

public class NoteException extends Exception {

    public NoteException(String message) {
        super(message);
    }

    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }
}
