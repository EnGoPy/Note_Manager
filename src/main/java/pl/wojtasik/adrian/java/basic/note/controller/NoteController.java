package pl.wojtasik.adrian.java.basic.note.controller;

import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.NoteFiltering;
import pl.wojtasik.adrian.java.basic.note.exception.AddNoteException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;
import pl.wojtasik.adrian.java.basic.note.exception.ReadNoteException;
import pl.wojtasik.adrian.java.basic.note.service.NoteService;

import java.util.List;

public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    public List<NoteModel> allNotes() {
        try {
            return noteService.list();
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNote(NoteModel noteModel) {
        try {
            noteService.create(noteModel);
        } catch (AddNoteException e) {
            String message = e.getMessage();
            System.out.println(message);
//            e.printStackTrace();
        }
    }

    public NoteModel read(Long id) {
        try {
            NoteModel noteModel = noteService.list(id);
            if (noteModel != null) {
                return noteModel;
            } else {
                System.out.println("There is no Note with id = " + id);
            }
        } catch (ReadNoteException e) {
            e.getMessage();
//            e.printStackTrace();
        }
        return null;
    }

    public List<NoteModel> list(NoteFiltering noteFiltering) {
        try {
            return noteService.list(noteFiltering);
        } catch (ReadNoteException e) {
            e.getMessage();
        }
        return null;
    }

    public void update(long id, NoteModel noteModel){
        try{
            noteService.update(id, noteModel);
        } catch (ReadNoteException e){
            e.getMessage();
        }
    }


}
