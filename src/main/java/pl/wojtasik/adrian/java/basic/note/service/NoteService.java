package pl.wojtasik.adrian.java.basic.note.service;

import pl.wojtasik.adrian.java.basic.note.dao.NoteDao;
import pl.wojtasik.adrian.java.basic.note.dao.NoteFiltering;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.AddNoteException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;
import pl.wojtasik.adrian.java.basic.note.exception.ReadNoteException;

import java.util.List;

public class NoteService {

    private NoteDao noteDao;

    public NoteService() {
    }

    public NoteService(NoteDao noteDao) {
           this.noteDao = noteDao;
    }

    public List<Note> list() throws NoteException {
        return noteDao.list();
    }

    public void create(Note note) throws AddNoteException{
        try {
            noteDao.create(note);
        } catch (NoteException e) {
            e.printStackTrace();
        }
    }

    public Note list(Long id) throws ReadNoteException{
        try {
            if(noteDao.read(id)!=null){
                return noteDao.read(id);
            }else{
                System.out.println("There is no row numbered "+id);
            }
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Note> list(NoteFiltering noteFiltering) throws ReadNoteException{
        try{
            return noteDao.list(noteFiltering);
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }



}
