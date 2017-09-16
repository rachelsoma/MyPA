package com.example.rhardie.mypa;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainFriendActivity extends BaseActivity {

    DatabaseManager db;
    ListView lv;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_friend);
        initToolbar();

        lv = (ListView) findViewById(R.id.friendsListView);
        db = new DatabaseManager(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainFriendActivity.this, AddFriendActivity.class);
                startActivity(intent);
            }
        });

        //test data into table
        showRec();

    }

    public void showRec() {
        lv.setAdapter(db.retrieveFriends(this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) lv.getItemAtPosition(position);
                Intent intentFriend = new Intent(MainFriendActivity.this, ViewFriendActivity.class);

                cursor.getString((cursor.getColumnIndex("gender")));
                String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
                String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                String friendName =
                         firstName + " " + lastName;
                String friendGender =
                        cursor.getString((cursor.getColumnIndex("gender")));
                String friendAge =
                        cursor.getString((cursor.getColumnIndex("age")));
                String friendAddress =
                        cursor.getString((cursor.getColumnIndex("address")));
                String friendSuburb =
                        cursor.getString((cursor.getColumnIndex("suburb")));
                String friendState =
                        cursor.getString((cursor.getColumnIndex("state")));

                intentFriend.putExtra("friendPosition", (cursor.getLong(cursor.getColumnIndex("_id"))));
                intentFriend.putExtra("extraName", friendName);
                intentFriend.putExtra("extraFname",firstName);
                intentFriend.putExtra("extraLname",lastName);
                intentFriend.putExtra("gender", friendGender);
                intentFriend.putExtra("age", friendAge);
                intentFriend.putExtra("address", friendAddress);
                intentFriend.putExtra("suburb", friendSuburb);
                intentFriend.putExtra("state", friendState);
                startActivity(intentFriend);
            }
        });
    }
}
