package pl.wojtasik.adrian.java.basic.note.dao;

import pl.wojtasik.adrian.java.basic.note.exception.ConnectionException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteDatabaseAccessException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtils {

    private static final Logger LOGGER= Logger.getLogger(DatabaseUtils.class.getName());

    private static Connection connection;

    private DatabaseUtils() {
    }

    public static Connection createConnection() throws NoteException {
        if (connection == null) {
            try {
                Properties properties = readProperties();
                System.out.println("PROPERTIES: "+properties);
                connection = DriverManager.getConnection(
                        properties.getProperty(NoteTable.URL),
                        properties.getProperty(NoteTable.USERNAME),
                        properties.getProperty(NoteTable.PASSWORDS));

                LOGGER.log(Level.INFO, "Connection acquired");
            } catch (SQLException e) {
                throw new ConnectionException("Cannot connect to DB", e);
//            } catch (NoteDatabaseAccessException e) {
//                throw new NoteDatabaseAccessException(e.getMessage(), e.getCause());
            }
        }
        return connection;
    }
    public static void prepareDatabase() throws NoteException{
        dropTable();
        prepareTable();
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

    private static Properties readProperties() {
        Properties properties = new Properties();

        try {
            properties.load(DatabaseUtils.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private static void prepareTable() throws NoteDatabaseAccessException {
        try {
            PreparedStatement statement = connection.prepareStatement(NoteTable.CREATE_TABLE);
            statement.execute();
        } catch (SQLException e) {
            throw new NoteDatabaseAccessException("Cannot create table", e);
        }
    }

    public static void showTables() throws NoteDatabaseAccessException {
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

    private static void dropTable() throws NoteDatabaseAccessException {
        try {
            String query = "DROP TABLE " + NoteTable.NOTE_TABLE_NAME;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
