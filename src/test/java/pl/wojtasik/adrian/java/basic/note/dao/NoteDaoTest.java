package pl.wojtasik.adrian.java.basic.note.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NoteDaoTest {
    private NoteDao noteDao;

    @Before
    public void setUp() {
        noteDao = new NoteDao();
    }

    @After
    public void tearDown() throws NoteException {
        DatabaseUtils.prepareDatabase();
        DatabaseUtils.closeConnection();
        noteDao = null;
    }

    @Test
    public void givenCreateTwoNoteObject_whenNoteDaoCreateObjects_thenTitleAndContentEquals() throws NoteException {
        //Given
        Note note1 = new Note("title_1", "content_1");
        Note note2 = new Note("title_2", "content_2");
        //When
        noteDao.create(note1);
        noteDao.create(note2);

        //Then
        assertEquals("title_1", noteDao.read(1L).getTitle());
        assertEquals("content_1", noteDao.read(1L).getContent());

        assertEquals("title_2", noteDao.read(2L).getTitle());
        assertEquals("content_2", noteDao.read(2L).getContent());
    }

    @Test
    public void givenCreatedObject_whenNoteDaoUpdate_thenValuesAreUpdated() throws NoteException {
        //Given
        Note note1 = new Note(1L, "title_1", "content_1");
        Note note2 = new Note(2L, "title_2", "content_2");
        noteDao.create(note1);
        //When
        noteDao.update(1L, note2);
        //Then
        assertEquals("_ID wasn't update", String.valueOf(2L), String.valueOf(noteDao.read(2L).getId()));
        assertEquals("TITLE wasn't update", "title_2", noteDao.read(2L).getTitle());
        assertEquals("CONTENT wasn't update", "content_2", noteDao.read(2L).getContent());
    }

    @Test
    public void givenAddedThreeNotesToDataBase_whenDeleteOneNote_thenTwoNotesAreLeft() throws NoteException {
        Note note1 = new Note(1L, "title_1", "content_1");
        Note note2 = new Note(2L, "title_2", "content_2");
        Note note3 = new Note(3L, "title_3", "content_3");
        noteDao.create(note1);
        noteDao.create(note2);
        noteDao.create(note3);
        assertEquals("Notes wasn't add successfully", 3, noteDao.list().size());
        //When
        noteDao.delete(1L);
        //Then
        assertEquals("Didn't delete 1st Note", 2, noteDao.list().size());
    }

    @Test
    public void testList() {
        NoteFiltering noteFiltering = new NoteFiltering();
    }

    //Zrobione z Jackiem
    @Test
    public void givenNoObject_whenNoteDaoListAll_thenListSizeIsEmpty() throws NoteException {
        //Given
        //When
        List<Note> notes = noteDao.list();
        //Then
        assertEquals("List is not empty", 0, notes.size());
    }


}