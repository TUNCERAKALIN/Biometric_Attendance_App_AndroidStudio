package com.example.biometrics;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Entry {
    int _id;
    String _date;
    String _title;
    String _text;

    public Entry(){}
    public Entry(String _date, String _title, String _text){
        this._date = _date;
        this._title =_title;
        this._text = _text;
    }
    public static Entry createDiaryEntry(Context context, String title, String text){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String finalDate = dateFormat.format(date);
        Entry newEntry = new Entry(finalDate, title, text);
        return newEntry;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }
}
