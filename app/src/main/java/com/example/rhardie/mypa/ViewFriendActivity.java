package com.example.rhardie.mypa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;

public class ViewFriendActivity extends MainFriendActivity {

    String fullName;
    String gender;
    String age;
    String address;
    String suburb;
    String state;
    long friendPosition;
    String rowID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edit " + rowID, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), fullName + " deleted", Toast.LENGTH_LONG).show();
                db.deleteFriend(rowID);
                Intent back = new Intent(ViewFriendActivity.this, MainFriendActivity.class);
                startActivity(back);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFriendFromIntent();

        TextView tvName = (TextView)findViewById(R.id.tvFullName);
        tvName.setText(fullName);
        TextView tvGender = (TextView)findViewById(R.id.tvGender);
        tvGender.setText(gender);
        TextView tvAge = (TextView)findViewById(R.id.tvAge);
        tvAge.setText(age);
        TextView tvAddress = (TextView)findViewById(R.id.tvAddress);
        tvAddress.setText(address);
        TextView tvSuburb = (TextView)findViewById(R.id.tvSuburb);
        tvName.setText(suburb);
        TextView tvState = (TextView)findViewById(R.id.tvState);
        tvName.setText(state);


    }

    public void getFriendFromIntent() {
        Bundle extras = getIntent().getExtras();
        fullName = extras.getString("extraName");
        gender = extras.getString("gender");
        age = extras.getString("age");
        address = extras.getString("address");
        suburb = extras.getString("suburb");
        state = extras.getString("state");
        friendPosition = extras.getLong("friendPosition");
        rowID = Long.toString(friendPosition);

        //The key argument here must match that used in the other activity





    }
}
