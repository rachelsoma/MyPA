/**
 * Created by Rachel Hardie on 27/08/2017.
 * 300960 (Spring 2017) Mobile Applications Development
 * Kingswood, Thursday 1200
 */

package com.example.rhardie.mypa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.sql.RowId;
import java.util.ArrayList;

public class DatabaseManager {

    //create database
    public static final String DB_NAME = "DB_Assignment1";
    public static final int DB_VERSION = 2;

    //create tables
    private static final String CREATE_FRIENDS_TABLE
            = "CREATE TABLE FRIENDS"
            + " (firstName TEXT, lastName TEXT, gender TEXT, age INT, address TEXT, suburb TEXT, state TEXT);";
    private static final String CREATE_EVENT_TABLE
            = "CREATE TABLE EVENTS"
            + " (eventName TEXT, upcoming BOOL, eventDate DATETIME, addressEvent TEXT);";
    private static final String CREATE_TODO_TABLE
            = "CREATE TABLE TODO"
            + " (todoName TEXT, complete BOOL, dateAdded DATETIME, todoNotes TEXT);";


    public SQLiteDatabase db;

    //constructor
    public DatabaseManager(Context c) {
        SQLHelper helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }


    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper (Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_EVENT_TABLE);
            db.execSQL(CREATE_FRIENDS_TABLE);
            db.execSQL(CREATE_TODO_TABLE);
            addTestData(db);
        }

        private void addTestData(SQLiteDatabase db) {
            addFriend("Joe","Jones","male",29,"20 Rance Rd","Penrith","NSW", db);
            addFriend("Adam","Kinnell","male",22,"Somewhere","Penrith","NSW", db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Friends table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS EVENTS");
            db.execSQL("DROP TABLE IF EXISTS FRIENDS");
            db.execSQL("DROP TABLE IF EXISTS TODO");
            onCreate(db);
        }
    }

    private boolean addFriend(String fn, String ln,String g, int age, String addr, String suburb, String state,
                              SQLiteDatabase db) {
        synchronized(db) {

            ContentValues newEntry = new ContentValues();

            newEntry.put("firstName", fn);
            newEntry.put("lastName", ln);
            newEntry.put("gender", g);
            newEntry.put("age", age);
            newEntry.put("address",addr);
            newEntry.put("suburb",suburb);
            newEntry.put("state",state);
            try {
                db.insertOrThrow("FRIENDS", null, newEntry);
            } catch (Exception e) {
                Log.e("Error in inserting rows", e.toString());
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public boolean addFriend(String fn, String ln, String g, int age, String addr, String suburb, String state) {
        return addFriend(fn, ln, g, age, addr, suburb, state, this.db);
    }

    public CursorAdapter retrieveFriends(Context context) {
        ArrayList<String> friendsRows = new ArrayList<String>();
        String[] columns = new String[] {"firstName", "lastName",
                "gender", "age", "address", "suburb", "state",
                "ROWID AS _id"};
        Cursor cursor = db.query("FRIENDS", columns, null, null, null, null, null);
        cursor.moveToFirst();

        class FriendsCursorAdapter extends ResourceCursorAdapter {

            public FriendsCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String friendName =
                        cursor.getString(cursor.getColumnIndex("firstName")) + " " +
                        cursor.getString(cursor.getColumnIndex("lastName"));
                TextView name = (TextView) view ;
                name.setText(friendName);
                String friendID =
                        cursor.getString(cursor.getColumnIndex("_id"));
                String gender =
                        cursor.getString(cursor.getColumnIndex("gender"));
                String age =
                        cursor.getString(cursor.getColumnIndex("age"));
                String address =
                        cursor.getString(cursor.getColumnIndex("address"));
                String suburb =
                        cursor.getString(cursor.getColumnIndex("suburb"));
                String state =
                        cursor.getString(cursor.getColumnIndex("state"));


            }

        };

        return new FriendsCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public CursorAdapter retrieveFriends(Context context, int friendID) {
        String fID = String.valueOf(friendID);
        ArrayList<String> friendsRows = new ArrayList<String>();
        String[] columns = new String[] {"firstName", "lastName",
                "gender", "age", "address", "suburb", "state",
                "ROWID AS _id"};
        Cursor cursor = db.query("FRIENDS", columns, fID, null, null, null, null);
        cursor.moveToFirst();

        class FriendsCursorAdapter extends ResourceCursorAdapter {

            public FriendsCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String friendName =
                        cursor.getString(cursor.getColumnIndex("firstName")) + " " +
                                cursor.getString(cursor.getColumnIndex("lastName"));
                TextView fullName = (TextView) view ;
                fullName.setText(friendName);
                String gender =
                        cursor.getString((cursor.getColumnIndex("gender")));
                String age =
                        cursor.getString((cursor.getColumnIndex("age")));
                String address =
                        cursor.getString((cursor.getColumnIndex("address")));
                String suburb =
                        cursor.getString((cursor.getColumnIndex("suburb")));
                String state =
                        cursor.getString((cursor.getColumnIndex("state")));
            }

        };

        return new FriendsCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public void clearFriends()
    {
        db.delete("FRIENDS", null, null);
    }

    public void clearEvents()
    {
        db.delete("EVENTS", null, null);
    }

    public void clearTodo()
    {
        db.delete("TODO", null, null);
    }
}
