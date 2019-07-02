package pl.wojtasik.adrian.java.basic.note.dao;

import java.sql.Connection;

public class DatabaseUtils {

    private static Connection connection;

    private DatabaseUtils(){
    }

    public static Connection createConnection(){
        //Stworzyc polaczenie connection ktore bedzie singletonem

        return connection;
    }

    public static void closeConnection(){
        //zrobic bezpieczne zamkniecie polaczenia
    }


}
