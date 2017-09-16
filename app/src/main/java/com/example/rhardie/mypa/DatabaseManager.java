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
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseManager {

    //create database
    public static final String DB_NAME = "DB_Assignment1";
    public static final int DB_VERSION = 5;


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
        public SQLHelper(Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TableManager.CREATE_EVENT_TABLE);
            db.execSQL(TableManager.CREATE_FRIENDS_TABLE);
            db.execSQL(TableManager.CREATE_TODO_TABLE);
            addTestData(db);
        }

        private void addTestData(SQLiteDatabase db) {
            addFriend("Joe", "Jones", "male", 29, "20 Rance Rd", "Werrington", "NSW", db);
            addFriend("Adam", "Kinnell", "male", 22, "Somewhere", "Penrith", "NSW", db);
            addTodo("Mop floor", "Home", db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Friends table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + TableManager.TB_EVENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TableManager.TB_FRIENDS);
            db.execSQL("DROP TABLE IF EXISTS " + TableManager.TB_TODO);
            onCreate(db);
        }
    }

    private boolean addFriend(String fn, String ln, String g, int age, String addr, String suburb, String state,
                              SQLiteDatabase db) {
        synchronized (db) {

            ContentValues newEntry = new ContentValues();

            newEntry.put(TableManager.FRIEND_COL_FNAME, fn);
            newEntry.put(TableManager.FRIEND_COL_LNAME, ln);
            newEntry.put(TableManager.FRIEND_COL_GENDER, g);
            newEntry.put(TableManager.FRIEND_COL_AGE, age);
            newEntry.put(TableManager.FRIEND_COL_ADDRESS, addr);
            newEntry.put(TableManager.FRIEND_COL_SUBURB, suburb);
            newEntry.put(TableManager.FRIEND_COL_STATE, state);
            try {
                db.insertOrThrow(TableManager.TB_FRIENDS, null, newEntry);
            } catch (Exception e) {
                Log.e("Error in inserting rows", e.toString());
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    private boolean addTodo(String name, String location,
                            SQLiteDatabase db) {
        synchronized (db) {

            ContentValues newEntry = new ContentValues();

            newEntry.put(TableManager.TODO_COL_NAME, name);
            newEntry.put(TableManager.TODO_COL_LOCATION, location);
            newEntry.put(TableManager.TODO_COL_COMPLETE, 0);

            try {
                db.insertOrThrow(TableManager.TB_TODO, null, newEntry);
            } catch (Exception e) {
                Log.e("Error in inserting rows", e.toString());
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public boolean addFriend
            (String fn, String ln, String g, int age, String addr, String suburb, String state) {
        return addFriend(fn, ln, g, age, addr, suburb, state, this.db);
    }

    public boolean addTodo
            (String name, String location) {
        return addTodo(name, location, this.db);
    }

    public CursorAdapter retrieveFriends(Context context) {
        ArrayList<String> friendsRows = new ArrayList<String>();
        String[] columns = new String[]{
                TableManager.FRIEND_COL_FNAME,
                TableManager.FRIEND_COL_LNAME,
                TableManager.FRIEND_COL_GENDER,
                TableManager.FRIEND_COL_AGE,
                TableManager.FRIEND_COL_ADDRESS,
                TableManager.FRIEND_COL_SUBURB,
                TableManager.FRIEND_COL_STATE,
                "ROWID AS _id"};
        Cursor cursor = db.query(TableManager.TB_FRIENDS, columns, null, null, null, null, null);
        cursor.moveToFirst();

        class FriendsCursorAdapter extends ResourceCursorAdapter {

            public FriendsCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String friendName =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_FNAME)) + " " +
                                cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_LNAME));
                TextView name = (TextView) view;
                name.setText(friendName);
                String friendID =
                        cursor.getString(cursor.getColumnIndex("_id"));
                String gender =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_GENDER));
                String age =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_AGE));
                String address =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_ADDRESS));
                String suburb =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_SUBURB));
                String state =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_STATE));
            }

        }
        ;

        return new FriendsCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public CursorAdapter retrieveFriends(Context context, int friendID) {
        String fID = String.valueOf(friendID);
        ArrayList<String> friendsRows = new ArrayList<String>();
        String[] columns = new String[]{
                TableManager.FRIEND_COL_FNAME,
                TableManager.FRIEND_COL_LNAME,
                TableManager.FRIEND_COL_GENDER,
                TableManager.FRIEND_COL_AGE,
                TableManager.FRIEND_COL_ADDRESS,
                TableManager.FRIEND_COL_SUBURB,
                TableManager.FRIEND_COL_STATE,
                "ROWID AS _id"};
        Cursor cursor = db.query(TableManager.TB_FRIENDS, columns, fID, null, null, null, null);
        cursor.moveToFirst();

        class FriendsCursorAdapter extends ResourceCursorAdapter {

            public FriendsCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String friendName =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_FNAME)) + " " +
                                cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_LNAME));
                TextView name = (TextView) view;
                name.setText(friendName);
                String friendID =
                        cursor.getString(cursor.getColumnIndex("_id"));
                String gender =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_GENDER));
                String age =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_AGE));
                String address =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_ADDRESS));
                String suburb =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_SUBURB));
                String state =
                        cursor.getString(cursor.getColumnIndex(TableManager.FRIEND_COL_STATE));
            }

        }
        ;

        return new FriendsCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public CursorAdapter retrieveTodo(Context context) {
        ArrayList<String> todoRows = new ArrayList<String>();
        String[] columns = new String[]{
                TableManager.TODO_COL_NAME,
                TableManager.TODO_COL_LOCATION,
                TableManager.TODO_COL_COMPLETE,
                "ROWID AS _id"};
        Cursor cursor = db.query(TableManager.TB_TODO, columns, null, null, null, null, null);
        cursor.moveToFirst();

        class TodoCursorAdapter extends ResourceCursorAdapter {

            public TodoCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String todoName =
                        cursor.getString(cursor.getColumnIndex(TableManager.TODO_COL_NAME));
                TextView name = (TextView) view;
                name.setText(todoName);
                String todoID =
                        cursor.getString(cursor.getColumnIndex("_id"));
                String todoLocation =
                        cursor.getString(cursor.getColumnIndex(TableManager.TODO_COL_LOCATION));
                String complete =
                        cursor.getString(cursor.getColumnIndex(TableManager.TODO_COL_COMPLETE));
            }

        }
        ;

        return new TodoCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public CursorAdapter retrieveTodo(Context context, int complete) {
        ArrayList<String> todoRows = new ArrayList<String>();

        String[] columns = new String[]{
                TableManager.TODO_COL_NAME,
                TableManager.TODO_COL_LOCATION,
                TableManager.TODO_COL_COMPLETE,
                "ROWID AS _id"};
        Cursor cursor = db.query
                (TableManager.TB_TODO, columns, TableManager.TODO_COL_COMPLETE + " = " + complete, null, null, null, null);
        cursor.moveToFirst();

        class TodoCursorAdapter extends ResourceCursorAdapter {

            public TodoCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
                super(context, layout, cursor, flags);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String todoName =
                        cursor.getString(cursor.getColumnIndex(TableManager.TODO_COL_NAME));
                TextView name = (TextView) view;
                name.setText(todoName);
                String todoID =
                        cursor.getString(cursor.getColumnIndex("_id"));
                String todoLocation =
                        cursor.getString(cursor.getColumnIndex(TableManager.TODO_COL_LOCATION));
                int complete =
                        cursor.getInt(cursor.getColumnIndex(TableManager.TODO_COL_COMPLETE));
            }
        }
        ;

        return new TodoCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, 0);
    }

    public void toggleTodo(long idRow, int complete) {
        ContentValues toggle = new ContentValues();
        if (complete == 0) { //0 is equal false
            toggle.put(TableManager.TODO_COL_COMPLETE, 1);
        } else {
            toggle.put(TableManager.TODO_COL_COMPLETE, 0);
        }
        db.update(TableManager.TB_TODO, toggle, "rowid =" + idRow, null);
    }

    public void updateFriend(long idRow, String fName, String lName, String gender, int age, String address, String suburb, String state) {
        ContentValues update = new ContentValues();

        update.put(TableManager.FRIEND_COL_FNAME,fName);
        update.put(TableManager.FRIEND_COL_LNAME,lName);
        update.put(TableManager.FRIEND_COL_GENDER,gender);
        update.put(TableManager.FRIEND_COL_AGE,age);
        update.put(TableManager.FRIEND_COL_ADDRESS,address);
        update.put(TableManager.FRIEND_COL_SUBURB,suburb);
        update.put(TableManager.FRIEND_COL_STATE,state);

        db.update(TableManager.TB_FRIENDS, update, "rowid =" + idRow, null);
    }

    public void deleteFriend(String idRow) {
        db.execSQL("DELETE FROM " + TableManager.TB_FRIENDS + " WHERE " + "ROWID" + "='" + idRow + "'");
    }

    public void clearFriends() {
        db.delete(TableManager.TB_FRIENDS, null, null);
    }

    public void clearEvents() {
        db.delete(TableManager.TB_EVENTS, null, null);
    }

    public void clearTodo() {
        db.delete(TableManager.TB_TODO, null, null);
    }
}

//making a goddamn change