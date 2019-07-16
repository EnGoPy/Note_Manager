package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteDatabaseAccessException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.sql.*;

public class DatabaseUtils {

//    public static final String DBNAME = "test";
//    public static final String ADRESS = "jdbc:h2:~/";

    private static Connection connection;

    private DatabaseUtils() {
    }

    public static Connection createConnection() throws NoteException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(NoteTable.PATH, NoteTable.USERNAME, NoteTable.PASSWORDS);
                dropTable();
                prepareTable();
                System.out.println("Created");
                showTables();
            } catch (SQLException e) {
                throw new ConnectionException("Cannot connect to DB", e);
//            } catch (NoteDatabaseAccessException e) {
//                throw new NoteDatabaseAccessException(e.getMessage(), e.getCause());
            }
        }
        return connection;
    }

    private static void prepareTable() throws NoteDatabaseAccessException {
        try {
            PreparedStatement statement = connection.prepareStatement(NoteTable.CREATE_TABLE);
            statement.execute();
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Cannot create table", e);
        }
    }

    private static void showTables() throws NoteDatabaseAccessException {
        try {
            String query = "SHOW COLUMNS FROM " + NoteTable.NOTE_TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void dropTable() throws NoteDatabaseAccessException{
        try{
            String query = "DROP TABLE " + NoteTable.NOTE_TABLE_NAME;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printTables() {

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
