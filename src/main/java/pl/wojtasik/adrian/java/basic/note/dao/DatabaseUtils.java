package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public static final String DBNAME = "notes.db";
    public static final String ADRESS = "jdbc:sqlite:/home/engopy/programming/Java_jacek_kurs/cli-notes/src/main/java/pl/wojtasik/adrian/java/basic/note/dao/"+DBNAME;

    public static final String PATH = ADRESS+DBNAME;

    private static Connection connection;

    private DatabaseUtils() {
    }

    public static Connection createConnection() throws ConnectionException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(PATH);
            } catch (SQLException e) {
                throw new ConnectionException("Cannot connect to DB", e);
            }
        }
        return connection;
    }

    public static void closeConnection() throws ConnectionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionException("Cannot close connection with DB", e);
            }
        }
    }


}
