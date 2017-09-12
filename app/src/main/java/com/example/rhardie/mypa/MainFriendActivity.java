package com.example.rhardie.mypa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

        ArrayList<String> tableContent = db.retrieveFriends();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, tableContent);

        lv.setAdapter(adapter);

    }
}
