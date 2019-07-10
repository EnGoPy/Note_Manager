package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteDatabaseAccessException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NoteDao {

    private static final String READ_NOTE = "";
    private static final String NOTE_TITLE_COLUMN = "title";
    private static final String NOTE_CONTENT_COLUMN = "content";
    private static final String LIST_NOTES = "";
    private Connection connection;
    private List<Note> notes;

    public NoteDao() {
        this.notes = new ArrayList<Note>();
    }

    public List<Note> list(NoteFiltering filtering) {

//        SELECT column_name(s)
//                FROM table_name
//        WHERE column_name BETWEEN ? AND ?;

        //PreparedStatement statement ...
        //statement.setInt( filtering.getStart());
        //statement.setInt( filtering.getOffset());
        return null;
    }

    public List<Note> list() throws NoteException{
        List<Note> notes= new ArrayList<>();
        try{
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(LIST_NOTES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                   Note note = new Note();
                   note.setTitle(resultSet.getString(NOTE_TITLE_COLUMN));
                   note.setContent(resultSet.getString(NOTE_CONTENT_COLUMN));
                   notes.add(note);
            }
        }catch (SQLException e){
            throw new NoteDatabaseAccessException("Failed to retreive notes", e);
        }
        return notes;
    }

    public void create(Note noteToAdd) throws NoteException {
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.CREATE_NOTE);
            statement.setString(1, noteToAdd.getTitle());
            statement.setString(2, noteToAdd.getContent());
            statement.execute();
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to create Note", e);
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        }

    }

    public Note read(Long id) throws NoteException {
        Note note = null;
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(READ_NOTE);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString(NOTE_TITLE_COLUMN);
                String content = resultSet.getString(NOTE_CONTENT_COLUMN);
                note = new Note(title, content);
//                return note;
            }
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to read note", e);
        } catch (ConnectionException e){
            throw new ConnectionException("Failed to connect to DB", e);
        }
        return note;
    }




}
