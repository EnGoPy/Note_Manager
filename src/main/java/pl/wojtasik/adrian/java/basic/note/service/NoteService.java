package pl.wojtasik.adrian.java.basic.note.service;

import pl.wojtasik.adrian.java.basic.note.dao.NoteDao;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.AddNoteException;
import pl.wojtasik.adrian.java.basic.note.exception.ReadNoteException;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class NoteService {

    private NoteDao noteDao;

    public NoteService() {
    }

    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public List<Note> list(){
        return noteDao.list();
    }

    public void create(Note note) throws AddNoteException{
        try {
            noteDao.create(note);
        } catch (SQLException e) {
            throw new AddNoteException("Blad podczas dodawania notatki", e);
        } catch (TimeoutException e) {
            throw new AddNoteException("Blad podczas tworzenia notatki", e);
        }
    }

    public Note read(Long id) throws ReadNoteException{
        try {
            return noteDao.read(id);
        } catch (SQLException | TimeoutException e) {
            throw new ReadNoteException("Blad podczas odczytu notatki", e);
        }
    }


}
