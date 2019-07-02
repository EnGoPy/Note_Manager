package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static pl.wojtasik.adrian.java.basic.note.dao.NoteTable.TABLE;

public class NoteDao {

    private Connection connection;
    private List<Note> notes;

    public NoteDao() {
        this.connection = DatabaseUtils.createConnection();
        this.notes=new ArrayList<Note>();
    }

    public List<Note> list(){
        return notes;
//        return Collections.unmodifiableList(list);
//        return new ArrayList<Note>(list);
    }

    public void create(Note noteToAdd) throws SQLException, TimeoutException {
        String tableName = TABLE;
        notes.add(noteToAdd);
        throw new SQLException();
    }

    public Note read(Long id) throws SQLException, TimeoutException{
        throw new SQLException();
//        return null;
    }



}
