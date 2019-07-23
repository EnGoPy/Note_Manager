package pl.wojtasik.adrian.java.basic.note.service;

import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.NoteDao;
import pl.wojtasik.adrian.java.basic.note.dao.NoteFiltering;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.AddNoteException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;
import pl.wojtasik.adrian.java.basic.note.exception.ReadNoteException;
import pl.wojtasik.adrian.java.basic.note.mapper.NoteMapper;

import java.util.ArrayList;
import java.util.List;

public class NoteService {

    private NoteDao noteDao;

    public NoteService() {
    }

    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public List<NoteModel> list() throws NoteException {
        List<NoteModel> noteModels = new ArrayList<>();

        List<Note> notes = noteDao.list();
        for (Note note : notes) {
            noteModels.add(NoteMapper.fromEntity(note));
        }

        return noteModels;
    }

    public void create(NoteModel noteModel) throws AddNoteException {
        try {
            noteDao.create(NoteMapper.fromModel(noteModel));
        } catch (NoteException e) {
            e.printStackTrace();
        }
    }

    public NoteModel list(Long id) throws ReadNoteException {
        try {
            return NoteMapper.fromEntity(noteDao.read(id));
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NoteModel> list(NoteFiltering noteFiltering) throws ReadNoteException {
        try {
            List<NoteModel> noteModels = new ArrayList<>();

            List<Note> notes = noteDao.list(noteFiltering);
            if (notes != null) {
                for (Note note : notes) {
                    noteModels.add(NoteMapper.fromEntity(note));
                }
            }
            return noteModels;
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Long id, NoteModel noteModel) throws ReadNoteException{
        try{
            noteDao.update(id, NoteMapper.fromModel(noteModel));
        } catch (NoteException e) {
            e.printStackTrace();
        }
    }


}
