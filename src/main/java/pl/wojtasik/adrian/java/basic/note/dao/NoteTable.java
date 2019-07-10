package pl.wojtasik.adrian.java.basic.note.dao;

public class NoteTable {


    public static final String TABLE_NAME = "NOTES";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_CONTENT = "CONTENT";

    public static final String CREATE_NOTE = "INSERT INTO "+TABLE_NAME+" ("+COLUMN_TITLE+" , "+COLUMN_CONTENT+") VALUES (?,?)";
    public static final String UPDATE_NOTE_BY_TITLE = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CONTENT+"=? WHERE "+COLUMN_TITLE+"=?";
    public static final String UPDATE_NOTE_BY_ID = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CONTENT+"=? WHERE "+COLUMN_ID+"=?";

//    public static final String FIND_NOTE_BY_ID = "SELECT "++" FROM "+TABLE_NAME+" SET "+COLUMN_CONTENT+"=? WHERE "+COLUMN_ID+"=?";




}
