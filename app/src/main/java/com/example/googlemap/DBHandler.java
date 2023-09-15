package com.example.googlemap;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_USER = "userdb"; // Database name
    private static final int DB_VERSION = 1; // Database version
    private static final String TABLE_NAME = "userinfo"; // Table name
    private static final String PHONE_NUMBER = "phoneNumber"; // Column name for phone number
    private static final String PASSWORD_COL = "password"; // Column name for password

    // Constructor
    public DBHandler(Context context) {
        super(context, DB_USER, null, DB_VERSION);
    }

    // Create the database table when it doesn't exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + PHONE_NUMBER + " TEXT, " // Added missing comma
                + PASSWORD_COL + " TEXT"
                + ")";
        db.execSQL(query);
    }

    // Upgrade the database (e.g., when version number changes)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert a user into the database
    public boolean insertUser(SQLiteDatabase db, String password, String phone) {
        ContentValues values = new ContentValues();
        values.put(PHONE_NUMBER, phone);
        values.put(PASSWORD_COL, password);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Retrieve all data from the table
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    // Update a user's phone number
    public void updatePhone(String oldPhone, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PHONE_NUMBER, newPhone);
        db.update(TABLE_NAME, values, PHONE_NUMBER + "=?", new String[]{oldPhone});
        db.close();
    }

    // Check if the old password matches a record in the database
    public boolean checkOldPassword(String oldPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + PASSWORD_COL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{oldPassword});
        boolean passwordMatch = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return passwordMatch;
    }

    // Update a user's password
    public void updatePassword(String oldPassword, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_COL, newPassword);
        db.update(TABLE_NAME, values, PASSWORD_COL + "=?", new String[]{oldPassword});
        db.close();
    }
}
