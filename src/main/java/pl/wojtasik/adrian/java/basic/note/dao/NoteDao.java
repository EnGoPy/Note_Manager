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

    private Connection connection;


    public void create(Note noteToAdd) throws NoteException {
        try {
            this.connection = DatabaseUtils.createConnection();
            List<Note> noteList = list();
            recount(noteList);
            PreparedStatement statement = connection.prepareStatement(NoteTable.CREATE_NOTE);
            statement.setString(1, String.valueOf(noteList.size() + 1));
            statement.setString(2, noteToAdd.getTitle());
            statement.setString(3, noteToAdd.getContent());
            statement.execute();
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to create Note", e);
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        }
    }

    public List<Note> list(NoteFiltering filtering) throws NoteException {
        List<Note> list = new ArrayList<>();
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.FIND_NOTE_BETWEEN);
            statement.setInt(1, (filtering.getStart()));
            statement.setInt(2, (filtering.getStart() + filtering.getOffset()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setTitle(resultSet.getString(NoteTable.NOTE_COLUMN_TITLE));
                note.setContent(resultSet.getString(NoteTable.NOTE_COLUMN_CONTENT));
                note.setId(resultSet.getLong(NoteTable.NOTE_COLUMN_ID));
                list.add(note);
            }
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to query by number ranges", e);
        }
        return list;
    }

    public List<Note> list() throws NoteException {
        List<Note> notes = new ArrayList<>();
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.LIST_NOTES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setTitle(resultSet.getString(NoteTable.NOTE_COLUMN_TITLE));
                note.setContent(resultSet.getString(NoteTable.NOTE_COLUMN_CONTENT));
                note.setId(resultSet.getLong(NoteTable.NOTE_COLUMN_ID));
                notes.add(note);
            }
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to retreive notes", e);
        }
        return notes;
    }

    private void recount(List<Note> notes) {
        if (notes.size() != 0) {
            long counter = 1;
            for (Note note : notes) {
                long tempId = note.getId();
                note.setId(counter);
                try {
                    update(tempId, note);
                } catch (NoteException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }
    }


    public Note read(Long id) throws NoteException {
        Note note = null;
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.READ_NOTE);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString(NoteTable.NOTE_COLUMN_TITLE);
                String content = resultSet.getString(NoteTable.NOTE_COLUMN_CONTENT);
                long noteId = resultSet.getLong(NoteTable.NOTE_COLUMN_ID);
                note = new Note(noteId, title, content);  // UWAGA! ID POBIERAM Z BAZY
            }
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to list note", e);
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        }
        return note;
    }

    public void update(Long id, Note note) throws NoteException {
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.UPDATE_NOTE_BY_ID);
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setLong(3, note.getId());
            statement.setLong(4, id);
            statement.execute();
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to update row", e);
        }
    }

    public void delete(Long id) throws NoteException {
        try {
            this.connection = DatabaseUtils.createConnection();
            PreparedStatement statement = connection.prepareStatement(NoteTable.DELETE_NOTE_BY_ID);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Failed to delete row", e);
        } catch (ConnectionException e) {
            throw new ConnectionException("Failed to connect to DB", e);
        }
    }


}
