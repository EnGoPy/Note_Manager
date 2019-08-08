package pl.wojtasik.adrian.java.basic.note.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.exception.NoteDatabaseAccessException;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DatabaseUtilsTest {
    private Connection connection;

    @After
    public void tearDown() throws SQLException {
        this.connection.close();
    }

    @Before
    public void setUp() throws NoteException {
        this.connection = DatabaseUtils.createConnection();
    }

   @Test
    public void printTablesTest() throws NoteDatabaseAccessException {
        DatabaseUtils.showTables(); //Jak?
    }

    @Test
    public void closeConnectionTest() throws SQLException {
        assertNotEquals(null, this.connection);
        connection.close();
        assertNull("Connection not closed", connection);
    }





}