package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    private static Connection connection=null;

    private DatabaseUtils(){}

    public static Connection createConnection() throws ConnectionException {
        //Stworzyc polaczenie connection ktore bedzie singletonem
        if(connection==null){
            try{
                 connection = DriverManager.getConnection(NoteTable.PATH);
            }
            catch (SQLException e){
                throw new ConnectionException("Cannot connect to DB", e);
            }
        }
        return connection;
    }

    public static void closeConnection() throws ConnectionException{
        //zrobic bezpieczne zamkniecie polaczenia
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionException("Cannot close connection with DB", e);
            }
        }
    }


}
