package com.example.biometrics;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "DiaryEntries.db";
    private static final String TABLE_ENTRIES="entries";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE ="date";
    private static final String KEY_TEXT ="text";

    public DiaryEntryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+TABLE_ENTRIES+"("
            +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATE + " TEXT,"
            +KEY_TITLE+" TEXT,"+KEY_TEXT+" TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+TABLE_ENTRIES;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    void addEntry(Entry diaryEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE, diaryEntry.get_date());
        values.put(KEY_TITLE, diaryEntry.get_title());
        values.put(KEY_TEXT, diaryEntry.get_text());
        db.insert(TABLE_ENTRIES, null, values);
        db.close();

    }
    public List<Entry> getAllEntries(){
        List<Entry> entryList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ENTRIES + "ORDER BY" + KEY_ID + "DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                Entry diaryEntry = new Entry();
                diaryEntry.set_id(Integer.parseInt(cursor.getString(0)));
                diaryEntry.set_date(cursor.getString(1));
                diaryEntry.set_title(cursor.getString(2));
                diaryEntry.set_text(cursor.getString(3));
                entryList.add(diaryEntry);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

}
