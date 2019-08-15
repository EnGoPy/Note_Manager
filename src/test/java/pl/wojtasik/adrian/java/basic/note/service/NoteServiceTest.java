package pl.wojtasik.adrian.java.basic.note.service;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.wojtasik.adrian.java.basic.note.controller.model.NoteModel;
import pl.wojtasik.adrian.java.basic.note.dao.NoteDao;
import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.AddNoteException;
import pl.wojtasik.adrian.java.basic.note.mapper.NoteMapper;

public class NoteServiceTest {

    private NoteService noteService;
    private static final String title1 = "Title_1";
    private static final String title2 = "Title_2";
    private static final String title3 = "Title_3";
    private static final String title4 = "Title_4";

    private static final String content1 = "Content_1";
    private static final String content2 = "Content_2";
    private static final String content3 = "Content_3";
    private static final String content4 = "Content_4";

    @Before
    public void setUp() throws AddNoteException {

    }
    @Mock
    NoteDao noteDao = Mockito.mock(NoteDao.class);
    Note note = Mockito.mock(Note.class);
    NoteMapper noteMapper = Mockito.mock(NoteMapper.class);
    NoteModel noteModel = Mockito.mock(NoteModel.class);


}

