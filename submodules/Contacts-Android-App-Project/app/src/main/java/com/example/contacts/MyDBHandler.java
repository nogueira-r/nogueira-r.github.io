package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE = "Contacts";
    public static final String ID = "Id";

    public static final String NAME = "Name";
    public static final String NUMBER = "Number";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + "("
                + ID + " INTEGER PRIMARY KEY, "
                + NAME + " TEXT, "
                + NUMBER + " TEXT)";
        db.execSQL(query);
    }

    public void addNewContact(String contactName, String contactNumber) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, contactName);
        values.put(NUMBER, contactNumber);

        db.insert(TABLE, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public ArrayList<ContactModel> readContacts() {SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE, null);

        ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();

        if (cursorContacts.moveToFirst()) {
            do {
                contactModelArrayList.add(new ContactModel(cursorContacts.getString(1),
                        cursorContacts.getString(2)));
            } while (cursorContacts.moveToNext());
        }
        cursorContacts.close();
        return contactModelArrayList;
    }

    public void updateContact(String originalContactName, String contactName, String contactNumber) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, contactName);
        values.put(NUMBER, contactNumber);

        db.update(TABLE, values, "name=?", new String[]{originalContactName});
        db.close();
    }

    public void deleteContact(String contactName) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "name=?", new String[]{contactName});
        db.close();
    }
}


