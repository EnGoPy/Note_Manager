package pl.wojtasik.adrian.java.basic.note.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.util.List;

public class NoteDaoTest {
    private NoteDao noteDao;

    @Before
    public void setUp() {
        noteDao = new NoteDao();
    }

    @After
    public void tearDown() throws ConnectionException {
        DatabaseUtils.closeConnection();
        noteDao = null;
    }

//    @Test//Jak testowac VOID?
//    public void createTest() throws NoteException {
//        //Given
//        Note note = new Note();
//        //When
//        noteDao.create(note);
//        Assert.assertNotNull();
//    }
    @Test
    public void testListi(){
        NoteFiltering noteFiltering = new NoteFiltering();
    }

    //Zrobione z Jackiem
    @Test
    public void givenNoObject_whenNoteDaoListAll_thenListSizeIsEmpty() throws NoteException {
        //Given
        //When
        List<Note> notes = noteDao.list();
        //Then
        Assert.assertEquals("List is not empty", 0, notes.size());
    }




    //Dodac testy jednostkowe dla CRUD'a
    //

}