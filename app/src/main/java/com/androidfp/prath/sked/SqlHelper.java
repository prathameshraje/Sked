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

    String result1,result2,result3,date;
    int count1, count2,count3;
    SQLiteDatabase db;
    // Database Version
    private static final int DATABASE_VERSION = 10;
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
    private static final String EVENT_SCHEDULED = "Scheduled";

    private static final String TABLE_CREATE_EVENTS = "CREATE TABLE " + TABLE_NAME_EVENTS
            + " ( " + EVENT_ID + " TEXT PRIMARY KEY, "
            + EVENT_NAME + " TEXT NOT NULL, "
            + EVENT_DESC + " TEXT, "
            + EVENT_TIMEFRAME + " INTEGER NOT NULL, "
            + EVENT_DAYONE + " TEXT, "
            + EVENT_DAYTWO + " TEXT, "
            + EVENT_DAYTHREE + " TEXT,"
            + EVENT_SCHEDULED + " TEXT DEFAULT NO);";

    // Event Members table name
    private static final String TABLE_NAME_EVENT_MEMBERS= "event_members";
    // Event Members Table Columns names
    private static final String EVENT_INITIATOR = "is_initiator";

    private static final String TABLE_CREATE_EVENT_MEMBERS = "CREATE TABLE " + TABLE_NAME_EVENT_MEMBERS
            + " ( " + EVENT_ID + " TEXT NOT NULL, "
            + KEY_ID + " INTEGER NOT NULL, "
            + EVENT_INITIATOR + " TEXT, PRIMARY KEY(" + EVENT_ID + "," + KEY_ID + "));";

    // Availability table name
    private static final String TABLE_NAME_AVAILABILITY= "availability";
    // Availability Table Columns names
    private static final String ID = "rid";
    private static final String DATE = "date";
    private static final String TIME = "time";

    private static final String TABLE_CREATE_AVAILABILITY = "CREATE TABLE " + TABLE_NAME_AVAILABILITY
            + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EVENT_ID + " TEXT NOT NULL, "
            + KEY_ID + " INTEGER NOT NULL, "
            + DATE + " TEXT, "
            + TIME + " TEXT);";

/*
    // Availability table name
    private static final String TABLE_NAME_AVAILABILITY= "availability";
    // Availability Table Columns names
    private static final String TSA1 = "tsa1";
    private static final String TSA2 = "tsa2";
    private static final String TSA3 = "tsa3";
    private static final String TSA4 = "tsa4";
    private static final String TSA5 = "tsa5";
    private static final String TSB1 = "tsb1";
    private static final String TSB2 = "tsb2";
    private static final String TSB3 = "tsb3";
    private static final String TSB4 = "tsb4";
    private static final String TSB5 = "tsb5";
    private static final String TSC1 = "tsc1";
    private static final String TSC2 = "tsc2";
    private static final String TSC3 = "tsc3";
    private static final String TSC4 = "tsc4";
    private static final String TSC5 = "tsc5";

    private static final String TABLE_CREATE_AVAILABILITY = "CREATE TABLE " + TABLE_NAME_AVAILABILITY
            + " ( " + EVENT_ID + " TEXT, "
            + KEY_ID + " INTEGER NOT NULL, "
            + TSA1 + " TEXT, "
            + TSA2 + " TEXT, "
            + TSA3 + " TEXT, "
            + TSA4 + " TEXT, "
            + TSA5 + " TEXT, "
            + TSB1 + " TEXT, "
            + TSB2 + " TEXT, "
            + TSB3 + " TEXT, "
            + TSB4 + " TEXT, "
            + TSB5 + " TEXT, "
            + TSC1 + " TEXT, "
            + TSC2 + " TEXT, "
            + TSC3 + " TEXT, "
            + TSC4 + " TEXT, "
            + TSC5 + " TEXT, PRIMARY KEY(" + EVENT_ID + "," + KEY_ID + "));";
*/

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
        db.execSQL(TABLE_CREATE_AVAILABILITY);
        Log.d("AVAILABILTY TBL CREATED", "YES");
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
        String TABLE_DROP_AVAILABILITY= "DROP TABLE IF EXISTS " + TABLE_NAME_AVAILABILITY;
        db.execSQL(TABLE_DROP_AVAILABILITY);
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
        String query = "SELECT name,description,events.event_id FROM " + TABLE_NAME_EVENTS + " INNER JOIN " + TABLE_NAME_EVENT_MEMBERS +
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
                event.setEventID(cursor.getString(2)); Log.d("Event ID", cursor.getString(2));
                // Add book to books
                events.add(event);
            } while (cursor.moveToNext());
        }

        Log.d("getAllEvents()", events.toString());

        return events; // return books
    }

    //Method to check if an event is already added in the event list of user
    public boolean eventExists(String strID, int userID) {
        db = this.getReadableDatabase();
        String search = "SELECT * FROM " +TABLE_NAME_EVENT_MEMBERS + " WHERE event_id='" + strID + "' AND id=" + userID + ";";
        Cursor cursorEvent = db.rawQuery(search, null);
        cursorEvent.moveToFirst();
        if(cursorEvent.getCount()==0)
            return false;
        else
            return true;

    }

    //Method to check if the Event ID id a valid ID
    public boolean validEvent(String strID){
        db = this.getReadableDatabase();
        String search = "SELECT * FROM " +TABLE_NAME_EVENTS + " WHERE event_id='" + strID + "';";
        Cursor cursorEvent = db.rawQuery(search, null);
        cursorEvent.moveToFirst();
        if(cursorEvent.getCount()==0)
            return false;
        else
            return true;
    }

    //Method to get the number of sked days (timeframe)
    public int getTimeframe(String eventID) {
        db = this.getReadableDatabase();
        String search = "SELECT timeframe FROM " +TABLE_NAME_EVENTS + " WHERE event_id='" + eventID + "'";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //Method to get the event details
    public Event getEventDetails(String eventID) {
        Event event = new Event();
        db = this.getReadableDatabase();
        String search = "SELECT * FROM " +TABLE_NAME_EVENTS + " WHERE event_id='" + eventID + "'";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        event.setEventName(cursor.getString(1));
        event.setEventDesc(cursor.getString(2));
        event.setEventTimeFrame(cursor.getInt(3));
        event.setEventDayOne(cursor.getString(4));
        event.setEventDayTwo(cursor.getString(5));
        event.setEventDayThree(cursor.getString(6));
        return event;
    }

    public void addAvailability(String eventID,int userID, String arr[], String eventDayOne, String eventDayTwo,String eventDayThree) {
        Log.d("Availability", "Reached");

        for(int i=0;i<arr.length;i++) {
            // Get reference to writable DB
            db = this.getWritableDatabase();

            // create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put(EVENT_ID, eventID); // get event ID
            values.put(KEY_ID, userID); // get user ID
            if(i<5)
            values.put(DATE, eventDayOne);
            if(i>4 && i<10)
                values.put(DATE, eventDayTwo);
            if(i>9 && i<15)
                values.put(DATE, eventDayThree);
            values.put(TIME, arr[i]);
            db.insert(TABLE_NAME_AVAILABILITY, null, values); // Insert the values into DB
        }

/*
        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(EVENT_ID, eventID); // get event ID
        values.put(KEY_ID, userID); // get user ID
        values.put(TSA1, arr[0]);values.put(TSA2, arr[1]);
        values.put(TSA3, arr[2]);values.put(TSA4, arr[3]);
        values.put(TSA5, arr[4]);
        values.put(TSB1, arr[5]);values.put(TSB2, arr[6]);
        values.put(TSB3, arr[7]);values.put(TSB4, arr[8]);
        values.put(TSB5, arr[9]);
        values.put(TSC1, arr[10]);values.put(TSC2, arr[11]);
        values.put(TSC3, arr[12]);values.put(TSC4, arr[13]);
        values.put(TSC5, arr[14]);

        db.insert(TABLE_NAME_AVAILABILITY, null, values); // Insert the values into DB
*/
    }

    public String getSkedOne(String eventID,int timeFrame,String eventDayOne,String eventDayTwo, String eventDayThree) {
        if(timeFrame>=1) {
            db = this.getReadableDatabase();
            String search;
            search = "SELECT time,MAX(timecount) AS RESULT,date FROM (SELECT time,COUNT(time) as timecount,date FROM availability where event_id='"
                    + eventID + "'" + " and date='" + eventDayOne + "'" + " group by time);";
            Cursor cursor = db.rawQuery(search, null);
            cursor.moveToFirst();
            Log.d("Sked DB", cursor.getString(0));
            Log.d("Count", Integer.toString(cursor.getInt(1)));
            count1=cursor.getInt(1);
            result1=cursor.getString(0);
            date=cursor.getString(2);
            if(timeFrame==1)
                return date+ " " + result1;
        }
        if(timeFrame>=2) {
            db = this.getReadableDatabase();
            String search;
            search = "SELECT time,MAX(timecount) AS RESULT,date FROM (SELECT time,COUNT(time) as timecount,date FROM availability where event_id='"
                    + eventID + "'" + " and date='" + eventDayTwo + "'" + " group by time)";
            Cursor cursor = db.rawQuery(search, null);
            cursor.moveToFirst();
            Log.d("Sked DB2", cursor.getString(0));
            Log.d("Count2", Integer.toString(cursor.getInt(1)));
            count2=cursor.getInt(1);
            result2=cursor.getString(0);
            date=cursor.getString(2);
            if(timeFrame==2){
                if(count1>=count2)
                    return date+ " " + result1;
                else
                    return date+ " " + result2;
            }
        }
        if(timeFrame==3) {
            db = this.getReadableDatabase();
            String search;
            search = "SELECT time,MAX(timecount) AS RESULT,date FROM (SELECT time,COUNT(time) as timecount,date FROM availability where event_id='"
                    + eventID + "'" + " and date='" + eventDayThree + "'" + " group by time)";
            Cursor cursor = db.rawQuery(search, null);
            cursor.moveToFirst();
            Log.d("Sked DB3", cursor.getString(0));
            Log.d("Count3", Integer.toString(cursor.getInt(1)));
            count3=cursor.getInt(1);
            result3=cursor.getString(0);
            if(count1>=count2 && count1>=count3)
                return date+ " " + result1;
            if(count2>=count1 && count2>=count3)
                return date+ " " + result2;
            if(count3>=count1 && count3>=count2)
                return date+ " " + result3;
        }
        return null;
    }

    public boolean isScheduled(String eventID){
        db = this.getReadableDatabase();
        String search = "SELECT Scheduled FROM " +TABLE_NAME_EVENTS + " WHERE event_id='" + eventID + "'";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals("YES"))
            return true;
        else
            return false;
    }

    public void setScheduled(String eventID){
        db = this.getWritableDatabase();
        //String search = "UPDATE " +TABLE_NAME_EVENTS + " SET Scheduled=" + "'YES'" + " WHERE event_id='" + eventID + "'";
        ContentValues values = new ContentValues();
        values.put("Scheduled", "YES");
        // 3. updating row
        db.update(TABLE_NAME_EVENTS, //table
                values, // column/value
                EVENT_ID + " = ?", // selections
                new String[]{String.valueOf(eventID) }); //selection args
        // 4. close dbase
        db.close();
    }

    //Method to check if the user is authorised to generated schedule
    public boolean checkAuthority(String eventID, int userID){
        db = this.getReadableDatabase();
        String search = "SELECT is_initiator FROM " +TABLE_NAME_EVENT_MEMBERS + " WHERE event_id='" + eventID + "' AND id=" + userID + ";";
        Cursor cursor = db.rawQuery(search, null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals("YES"))
            return true;
        else
            return false;
    }
}
