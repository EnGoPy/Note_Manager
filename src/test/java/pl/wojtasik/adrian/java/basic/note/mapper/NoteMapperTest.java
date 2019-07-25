package pl.wojtasik.adrian.java.basic.note.mapper;

import org.junit.Assert;
import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;

public class NoteMapperTest {



    @Test
    public void givenNewNote_whenNoteMapperFromEntity_thenNoteModelIsNotNull() {
        //Given
        Note note = new Note();
        //When
        NoteModel noteModel = NoteMapper.fromEntity(note);
        //Then
        Assert.assertNotNull("Note is null", noteModel);

    }

    @Test
    public void givenNewNoteModel_whenNoteMapperFromModel_thenNoteIsNotNull() {
        //Given
        NoteModel noteModel = new NoteModel();
        //When
        Note note = NoteMapper.fromModel(noteModel);
        //Then
        Assert.assertNotNull("NoteModel is null", note);
    }
}