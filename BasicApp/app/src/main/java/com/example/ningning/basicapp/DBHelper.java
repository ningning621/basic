package com.example.ningning.basicapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ningning on 5/23/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int database_VERSION = 4;
    private static final String TAG = DBHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "EntriesDB";
    public static final String TABLE_NAME = "entries";
    public static final String ENTRIES_ID = "id";
    public static final String ENTRIES_DATE = "date";
    public static final String ENTRIES_TEXT = "text";
    public static final String ENTRIES_RATING = "rating";

    private static final String[] COLUMNS = {ENTRIES_ID, ENTRIES_DATE, ENTRIES_TEXT, ENTRIES_RATING };

    public DBHelper(Context context){

        super(context, DATABASE_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ENTRIES_TABLE = "CREATE TABLE entries ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "date TEXT, " + "text TEXT, " +"rating TEXT )";
        db.execSQL(CREATE_ENTRIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
