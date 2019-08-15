package pl.wojtasik.adrian.java.basic.note.dao;

import org.junit.Test;
import pl.wojtasik.adrian.java.basic.note.exception.NoteException;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseUtilsTest {

    @Test
    public void givenGetConnection_whenInvokedTwice_thanHaveTheSameConnection() throws NoteException {
        //Given
        Connection connection1;
        Connection connection2;
        //When
        connection1 = DatabaseUtils.getConnection();
        connection2 = DatabaseUtils.getConnection();
        //Than
        assertEquals("Connections aren't equals", connection1, connection2);
    }

    @Test
    public void givenGetConnection_whenConnectionClose_thanConnectionIsClosedReturnTrue() throws NoteException, SQLException {
        //Given
        Connection connection = DatabaseUtils.getConnection();
        //When
        DatabaseUtils.closeConnection();
        //Then
        assertTrue("Connection is not closed", connection.isClosed());
    }





}