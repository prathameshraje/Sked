package com.androidfp.prath.sked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by prath on 4/9/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    // Database Version
    private static final int DATABASE_VERSION = 7;
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

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT NOT NULL, "
            + KEY_EMAIL + " TEXT NOT NULL, "
            + KEY_PASSWORD + " TEXT NOT NULL, "
            + KEY_CONTACT + " TEXT NOT NULL);";

    // Events table name
    private static final String TABLE_NAME_EVENTS= "events";
    // Events Table Columns names
    private static final String EVENT_ID = "event_id";
    private static final String EVENT_NAME = "name";
    private static final String EVENT_DESC = "description";
    private static final String EVENT_TIMEFRAME = "timeframe";
    private static final String EVENT_DAYONE = "dayone";
    private static final String EVENT_DAYTWO = "daytwo";
    private static final String EVENT_DAYTHREE = "daythree";

    private static final String TABLE_CREATE_EVENTS = "CREATE TABLE " + TABLE_NAME_EVENTS
            + " ( " + EVENT_ID + " TEXT PRIMARY KEY, "
            + EVENT_NAME + " TEXT NOT NULL, "
            + EVENT_DESC + " TEXT, "
            + EVENT_TIMEFRAME + " INTEGER NOT NULL, "
            + EVENT_DAYONE + " TEXT, "
            + EVENT_DAYTWO + " TEXT, "
            + EVENT_DAYTHREE + " TEXT);";

    // Event Members table name
    private static final String TABLE_NAME_EVENT_MEMBERS= "event_members";
    // Event MEmbers Table Columns names
    private static final String EVENT_INITIATOR = "is_initiator";

    private static final String TABLE_CREATE_EVENT_MEMBERS = "CREATE TABLE " + TABLE_NAME_EVENT_MEMBERS
            + " ( " + EVENT_ID + " TEXT NOT NULL, "
            + KEY_ID + " INTEGER NOT NULL, "
            + EVENT_INITIATOR + " TEXT, PRIMARY KEY(" + EVENT_ID + "," + KEY_ID + "));";





    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        Log.d("USERS TABLE CREATED", "YES");
        db.execSQL(TABLE_CREATE_EVENTS);
        Log.d("EVENTS TABLE CREATED", "YES");
        db.execSQL(TABLE_CREATE_EVENT_MEMBERS);
        Log.d("EVNTMEMBERS TBL CREATED", "YES");
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String TABLE_DROP= "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(TABLE_DROP);
        String TABLE_DROP_EVENTS= "DROP TABLE IF EXISTS " + TABLE_NAME_EVENTS;
        db.execSQL(TABLE_DROP_EVENTS);
        String TABLE_DROP_EVENT_MEMBERS= "DROP TABLE IF EXISTS " + TABLE_NAME_EVENT_MEMBERS;
        db.execSQL(TABLE_DROP_EVENT_MEMBERS);
        this.onCreate(db);
    }

    //CRUD functions on USERS table

    public void addUser(User user) {
        Log.d("addUser", user.toString());
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

    public int getUserID(String loginID) {
        db = this.getReadableDatabase();
        String search = "SELECT id FROM " +TABLE_NAME + " WHERE email='" + loginID + "'";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //CRUD methods on Event table

    public void addEvent(Event event) {
        Log.d("addEvent", event.toString());
        // Get reference to writable DB
        db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(EVENT_ID, event.getEventID()); // get event ID
        values.put(EVENT_NAME, event.getEventName()); // get event name
        values.put(EVENT_DESC, event.getEventDesc()); // get event description
        values.put(EVENT_TIMEFRAME, event.getEventTimeFrame()); // get event time frame
        values.put(EVENT_DAYONE, event.getEventDayOne()); //get event day one date
        values.put(EVENT_DAYTWO, event.getEventDayTwo()); //get event day two date
        values.put(EVENT_DAYTHREE, event.getEventDayThree()); //get event day three date

        db.insert(TABLE_NAME_EVENTS, null, values); // Insert the values into DB

    }

    //CRUD methods on Event Members table
    public void addEventMember(String event_ID, int user_ID, String is_Initiator) {
        Log.d("addEventMembers", ""+event_ID+ " " + user_ID);
        // Get reference to writable DB
        db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(EVENT_ID, event_ID); // get event ID
        values.put(KEY_ID, user_ID); // get event name
        values.put(EVENT_INITIATOR, is_Initiator); // get event description

        db.insert(TABLE_NAME_EVENT_MEMBERS, null, values); // Insert the values into DB
    }

    // Get All Books
    public List<Event> getAllEvents(int user_id) {
        List<Event> events = new LinkedList<Event>();

        // 1. build the query
        String query = "SELECT name,description FROM " + TABLE_NAME_EVENTS + " INNER JOIN " + TABLE_NAME_EVENT_MEMBERS +
        " ON events.event_id=event_members.event_id WHERE event_members.id=" + user_id;

        // 2. get reference to writable DB
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Event event = null;
        if (cursor.moveToFirst()) {
            do {
                event = new Event();
                event.setEventName(cursor.getString(0)); Log.d("Name", cursor.getString(0));
                event.setEventDesc(cursor.getString(1)); Log.d("Desc", cursor.getString(1));
                // Add book to books
                events.add(event);
            } while (cursor.moveToNext());
        }

        Log.d("getAllEvents()", events.toString());

        return events; // return books
    }


    public boolean eventExists(String strID, int userID) {
        db = this.getReadableDatabase();
        String search = "SELECT * FROM " +TABLE_NAME_EVENT_MEMBERS + " WHERE event_id='" + strID + "' AND id=" + userID + ";";
        Cursor cursorEvent = db.rawQuery(search, null);
        cursorEvent.moveToFirst();
        if(cursorEvent.isNull(0))
            return false;
        else
            return true;

    }
}
