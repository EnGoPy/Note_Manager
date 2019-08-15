package pl.wojtasik.adrian.java.basic.note.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class NoteDaoTest {
    private NoteDao noteDao;

    private static final String title1 = "Title_1";
    private static final String title2 = "Title_2";
    private static final String title3 = "Title_3";

    private static final String content1 = "Content_1";
    private static final String content2 = "Content_2";
    private static final String content3 = "Content_3";

    @Before
    public void setUp() {
        noteDao = new NoteDao();
    }

    @After
    public void tearDown() throws NoteException, SQLException {
        //DatabaseUtils.resetDatabase();
        Connection connection = DatabaseUtils.getConnection();
        DatabaseUtils.closeConnection();
        noteDao = null;
    }

    @Test
    public void givenCreateTwoNoteObject_whenNoteDaoCreateObjects_thenTitleAndContentEquals() throws NoteException, SQLException {
        //NoteDao noteDao = Mockito.mock(NoteDao.class);
        //Note note1 = new Note(title1, content1);
        //Mockito.when(noteDao.create(note1)).thenReturn(note1);
        //Note createdNote = noteDao.create(note1);
        //assertEquals("Message", note1.getTitle(), createdNote.getTitle());
        try {
            //Given
            Note note1 = new Note(1L, title1, content1);
            //When
            Note createdNote = noteDao.create(note1);
            //Then
            Note readNote1 = noteDao.read(createdNote.getId());
            assertThat(note1).isEqualToComparingFieldByFieldRecursively(readNote1);
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            //connection.rollback();
            connection.commit();
        }
    }

    @Test
    public void givenCreatedObject_whenNoteDaoUpdate_thenObjectsValuesExceptIdAreUpdated() throws NoteException, SQLException {
        try {
            //Given
            Note note = new Note(title1, content1);
            Note updateNote = new Note(title2, content2);
            Note createdNote = noteDao.create(note);
            //When
            Long createdNoteId = createdNote.getId();
            noteDao.update(createdNoteId, updateNote);
            //Then
            Note read = noteDao.read(createdNoteId);
            assertThat(updateNote).isEqualToIgnoringGivenFields(read, "id");
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            connection.rollback();
        }
    }

    @Test
    public void givenAddedNoteToDataBase_whenDeleteNote_thenNoteDaoListSizeIsZero() throws NoteException, SQLException {
        try {
            Note note1 = new Note(title1, content1);
            Note createdNote = noteDao.create(note1);
            //When
            noteDao.delete(createdNote.getId());
            //Then
            List<Note> list = noteDao.list();
            assertEquals("Didn't delete 1st Note", 0, list.size());
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            connection.rollback();
        }
    }

    @Test(expected = NoteException.class) // DO OBGADANIA Z JACKIEM
    public void givenAddedNoteToDataBase_whenDeleteNoteWithWrongId_thenThrowsException() throws NoteException, SQLException {
        try {
            Note note1 = new Note(title1, content1);
            Note createdNote = noteDao.create(note1);
            //When
            noteDao.delete(createdNote.getId()); // Baza danych nie wyrzuca exception jeśli takiego rekordu nie ma w Bazie
            //Then
            //Exception Thrown
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            connection.rollback();
        }
    }

    @Test
    public void givenAddedNoteToDataBase_whenDeleteNoteById_thenNoteDaoListEqualsZero() throws NoteException, SQLException {
        try {
            Note note1 = new Note(1L, title1, content1);
            noteDao.create(note1);
            //When
            noteDao.delete(1L);
            //Then
            List<Note> noteList = noteDao.list();
            assertEquals("Note wasn't delete from DB", 0, noteList.size());
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            connection.rollback();
        }
    }

    @Test
    public void givenThreeNotesAndNoteFilteringWithTwoObject_whenNoteDaoFiltering_thenListSizeIs2() throws NoteException, SQLException {
        try {
            //Given
            Note note1 = new Note(title1, content1);
            Note note2 = new Note(title2, content2);
            Note note3 = new Note(title3, content3);
            noteDao.create(note1);
            noteDao.create(note2);
            noteDao.create(note3);
            NoteFiltering noteFiltering = new NoteFiltering();
            //When
            List<Note> listFiltering = noteDao.list(noteFiltering);
            //Then
            assertEquals("", 2, listFiltering.size());
        } finally {
            Connection connection = DatabaseUtils.getConnection();
            connection.rollback();
        }
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