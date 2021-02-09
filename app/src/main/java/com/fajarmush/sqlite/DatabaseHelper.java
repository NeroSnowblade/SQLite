package com.fajarmush.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contoh.db";
    private static final int DB_VERSION = 1;
    static final String TABLE_NAME = "contoh";
    static final String _ID = "_id";
    static final String TITLE = "Title";
    static final String DESC = "Description";

    // Table Query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID + "INTEGER PRIMARY KEY,"
            + TITLE + "TEXT NOT NULL," + DESC + "TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
