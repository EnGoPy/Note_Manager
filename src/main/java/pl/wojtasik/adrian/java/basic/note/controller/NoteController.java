package pl.wojtasik.adrian.java.basic.note.controller;

import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
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

    public List<Note> allNotes(){
        try {
            return noteService.list();
        } catch (NoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNote(Note note){
        try {
            noteService.create(note);
        } catch (AddNoteException e) {
            String message = e.getMessage();
            System.out.println(message);
//            e.printStackTrace();
        }
    }

    public Note read(Long id){
        try {
            return noteService.read(id);
        } catch (ReadNoteException e) {
            e.getMessage();
//            e.printStackTrace();
        }
        return null;
    }

    public void addNoteAndReadNote(){
        try {
            noteService.create(new Note());
            noteService.read(10L);
        } catch (NoteException e) {
            e.getMessage();
//            e.printStackTrace();
        }

    }


}
