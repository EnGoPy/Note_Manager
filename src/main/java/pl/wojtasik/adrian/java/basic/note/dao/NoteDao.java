package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.dao.entity.Note;
import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class NoteDao {

    private Connection connection;
    private List<Note> notes;

    public NoteDao(){
        this.notes=new ArrayList<Note>();
    }

    public List<Note> list(){
        return notes;
//        return Collections.unmodifiableList(list);
//        return new ArrayList<Note>(list);
    }

    public void create(Note noteToAdd) throws SQLException, ConnectionException{
        try{
            this.connection = DatabaseUtils.createConnection();
            //Czy otwierac polaczenie w konstruktorze klasy czy przy kazdym zapytanio do DB?

//            Connection conn = DatabaseUtils.createConnection();
            PreparedStatement createNote = connection.prepareStatement(NoteTable.CREATE_NOTE);
            createNote.setString(1, noteToAdd.getTitle());
            createNote.setString(2, noteToAdd.getContent());
            createNote.execute();
            }
            catch (SQLException e) {
                throw new SQLException();
            }catch (ConnectionException e){
                throw new ConnectionException("Failed to execute query", e);   ////<=== Dlaczego tu tak? A wyzej inaczej?
            }
            finally {
            this.connection.close();
        }
    }

    //Czy NoteDao obsluguje wyszukiwanie w DB?


    // Czy tutaj zwracac obiekt czy String/ResultSet?
    public Note read(Long id) throws SQLException{

        throw new SQLException();
//        return null;
    }



}
