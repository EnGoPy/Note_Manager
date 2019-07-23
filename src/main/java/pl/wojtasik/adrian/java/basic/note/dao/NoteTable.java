package pl.wojtasik.adrian.java.basic.note.dao;

public class NoteTable {


    //CONNECTION PROPERTIES
    public static final String URL ="datasource.url"; //"jdbc:h2:~/test";
    public static final String USERNAME = "datasource.user"; //"sa";
    public static final String PASSWORDS = "datasource.password";//"";

    // TABLE CONTENT
    public static final String NOTE_TABLE_NAME = "NOTES";
    public static final String NOTE_COLUMN_ID = "_ID";
    public static final String NOTE_COLUMN_TITLE = "TITLE";
    public static final String NOTE_COLUMN_CONTENT = "CONTENT";

    //SQL QUERIES
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NOTE_TABLE_NAME + "(" + NOTE_COLUMN_ID + " BIGINT UNSIGNED NOT NULL AUTO_INCREMENT , " + NOTE_COLUMN_TITLE + " TEXT, " + NOTE_COLUMN_CONTENT + " TEXT)";
    public static final String CREATE_NOTE = "INSERT INTO " + NOTE_TABLE_NAME + " (" + NOTE_COLUMN_TITLE + " , " + NOTE_COLUMN_CONTENT + ") VALUES (?,?)";
    public static final String FIND_NOTE = "SELECT * FROM " + NOTE_TABLE_NAME + " WHERE "+NOTE_COLUMN_TITLE+"=?";
    public static final String FIND_NOTE_BETWEEN = "SELECT * FROM " + NOTE_TABLE_NAME + " WHERE " + NOTE_COLUMN_ID + " BETWEEN ? AND ?";
    public static final String UPDATE_NOTE_BY_TITLE = "UPDATE " + NOTE_TABLE_NAME + " SET " + NOTE_COLUMN_CONTENT + "=? WHERE " + NOTE_COLUMN_TITLE + "=?";
    public static final String UPDATE_NOTE_BY_ID = "UPDATE " + NOTE_TABLE_NAME +" SET "+NOTE_COLUMN_TITLE +"=? , " + NOTE_COLUMN_CONTENT + "=?  WHERE " + NOTE_COLUMN_ID + "=?";
    static final String LIST_NOTES = "SELECT * FROM " + NOTE_TABLE_NAME;


    static final String READ_NOTE = "SELECT * FROM " + NOTE_TABLE_NAME + " WHERE " + NOTE_COLUMN_ID + "=?";

}
