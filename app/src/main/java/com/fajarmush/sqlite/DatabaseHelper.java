package com.fajarmush.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UserInfo";
    static final String TABLE_NAME = "tbl_user";
    static final String KEY_NAME = "_id";
    static final String KEY_AGE = "Title";

    // Table Query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_NAME + "TEXT PRIMARY KEY,"
            + KEY_AGE + "INTEGER" + ");";

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

    public void insert(PersonBean personBean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, personBean.getName());
        values.put(KEY_AGE, personBean.getAge());

        db.insert(TABLE_NAME, null, values);
    }

    public List<PersonBean> selectUserData() {
        ArrayList<PersonBean> userList = new ArrayList<PersonBean>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_NAME, KEY_AGE};

        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (c.moveToNext()) {
            String name = c.getString(0);
            int age = c.getInt(1);

            PersonBean personBean = new PersonBean();
            personBean.setName(name);
            personBean.setAge(age);
            userList.add(personBean);
        }
        return userList;
    }

    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_NAME + "='" + name + "'";
        db.delete(TABLE_NAME, whereClause, null);
    }

    public void  update(PersonBean personBean) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AGE, personBean.getAge());
        String whereClause = KEY_NAME + "='" + personBean.getName() + "'";
        db.update(TABLE_NAME, values, whereClause, null);
    }
}
