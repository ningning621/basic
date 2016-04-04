package com.example.ningning.basicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Ningning on 5/25/2015.
 */
public class EntryRepo {

    private DBHelper dbHelper;
    private static final String TAG = EntryRepo.class.getSimpleName();
    public static final String DATABASE_NAME = "EntriesDB";
    public static final String TABLE_NAME = "entries";
    public static final String ENTRIES_ID = "id";
    public static final String ENTRIES_DATE = "date";
    public static final String ENTRIES_TEXT = "text";
    public static final String ENTRIES_RATING = "rating";

    public EntryRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert (Entries e)
    {
        //create database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //set colomns
        contentValues.put(ENTRIES_DATE, e.getDate());
        contentValues.put(ENTRIES_TEXT, e.getText());
        contentValues.put(ENTRIES_RATING, e.getRating());

        long id = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return (int)id;
    }

    //find entry by id number
    public Entries getEntryById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                ENTRIES_ID + "," +
                ENTRIES_DATE + "," +
                ENTRIES_TEXT + "," +
                ENTRIES_RATING +
                " FROM " + TABLE_NAME
                + " WHERE " +
                ENTRIES_ID + "=?";

        int count = 0;
        Entries Sentry = new Entries();
        Cursor cursor =  db.rawQuery(selectQuery,new String[] {String.valueOf(id)});

        //goes through each item in the list and get the int and string until it reaches the last item
        if (cursor.moveToFirst()) {
            do {
                Sentry.setId(cursor.getInt(cursor.getColumnIndex(ENTRIES_ID)));
                Sentry.setDate(cursor.getString(cursor.getColumnIndex(ENTRIES_DATE)));
                Sentry.setText(cursor.getString(cursor.getColumnIndex(ENTRIES_TEXT)));
                Sentry.setRating(cursor.getString(cursor.getColumnIndex(ENTRIES_RATING)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        //return entry with id number
        return Sentry;
    }

    //get list of entries
    public ArrayList getEntriesList(){
        ArrayList<HashMap<String, String>> entriesList = new ArrayList<HashMap<String, String>>();
        String query = "SELECT " +
                ENTRIES_ID + "," +
                ENTRIES_DATE + "," +
                ENTRIES_TEXT + "," +
                ENTRIES_RATING +
                " FROM " + TABLE_NAME;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do{
                HashMap<String, String> entry = new HashMap<String, String>();
                entry.put("id", cursor.getString(cursor.getColumnIndex(ENTRIES_ID)));
                entry.put("date", cursor.getString(cursor.getColumnIndex(ENTRIES_DATE)));
                entry.put("text", cursor.getString(cursor.getColumnIndex(ENTRIES_TEXT)));
                entry.put("rating", cursor.getString(cursor.getColumnIndex(ENTRIES_RATING)));
                entriesList.add(entry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return entriesList;
    }

    //change info in entry
    public void update(Entries e)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTRIES_DATE, e.getDate());
        contentValues.put(ENTRIES_TEXT, e.getText());
        contentValues.put(ENTRIES_RATING, e.getRating());
        db.update(TABLE_NAME, contentValues, ENTRIES_ID + " = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
    }

//delete entry
    public void delete(Entries e)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, ENTRIES_ID + " = ? ",
                new String[] { String.valueOf(e.getId())});
        db.close();
    }


    //get all entries
    public List getAllEntriesList(){
        List entries = new LinkedList();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Entries entry = null;
        if(cursor.moveToFirst()) {
            do{
                entry = new Entries();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setDate(cursor.getString(1));
                entry.setText(cursor.getString(2));
                entry.setRating(cursor.getString(3));

                entries.add(entry);
            } while (cursor.moveToNext());
        }
        return entries;
    }
}
