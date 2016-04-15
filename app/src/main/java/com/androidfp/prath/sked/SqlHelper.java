package com.androidfp.prath.sked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Currency;

/**
 * Created by prath on 4/9/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "UserDB.db";

    // Books table name
    private static final String TABLE_NAME = "users";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONTACT = "contact";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL, "
            + KEY_EMAIL + " TEXT NOT NULL, "
            + KEY_PASSWORD + " TEXT NOT NULL, "
            + KEY_CONTACT + " TEXT NOT NULL);";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db=db;
        Log.d("CREATED", "YES");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String TABLE_DROP= "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(TABLE_DROP);
        this.onCreate(db);
    }

    public void addUser(User user) {
        Log.d("addBook", user.toString());
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // get User's Name
        values.put(KEY_EMAIL, user.getEmail()); // get User's Email ID
        values.put(KEY_PASSWORD, user.getPassword()); // get User's Password
        values.put(KEY_CONTACT, user.getContact()); // get User's Contact

        db.insert(TABLE_NAME, null, values); // Insert the values into DB

    }


    public String searchUser(String loginID) {
        String pwd = null;
        db = this.getReadableDatabase();
        String search = "SELECT password FROM " +TABLE_NAME + " WHERE email='" + loginID + "'";
        Cursor cursor = db.rawQuery(search, null);
        //boolean result = cursor.moveToFirst();
        //Log.d("result: ", String.valueOf(result));
        //if(result==true)
        if(cursor.moveToFirst())
        pwd = cursor.getString(0);
        //cursor.close();
        return pwd;
    }

    public String getUserName(String loginID) {
        db = this.getReadableDatabase();
        String search = "SELECT name FROM " +TABLE_NAME + " WHERE email='" + loginID + "'";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
}
