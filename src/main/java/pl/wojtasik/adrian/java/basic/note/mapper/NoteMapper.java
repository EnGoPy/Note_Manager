package pl.wojtasik.adrian.java.basic.note.mapper;

import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;

public class NoteMapper {

    public static NoteModel fromEntity(Note note) {
        NoteModel noteModel = null;
        if (note != null) {
            noteModel = new NoteModel();
            noteModel.setTitle(note.getTitle());
            noteModel.setContent(note.getContent());
            noteModel.setId(note.getId());
        }
        return noteModel;
    }

    public static Note fromModel(NoteModel noteModel) {
        Note note = null;
        if (noteModel != null) {
            note = new Note();
            note.setTitle(noteModel.getTitle());
            note.setContent(noteModel.getContent());
            note.setId(noteModel.getId());
        }
        return note;
    }
}
