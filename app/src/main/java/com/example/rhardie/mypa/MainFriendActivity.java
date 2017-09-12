package com.example.rhardie.mypa;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
                Toast.makeText(MainFriendActivity.this, "" + id, Toast.LENGTH_LONG).show();
            }
        });
    }
}
