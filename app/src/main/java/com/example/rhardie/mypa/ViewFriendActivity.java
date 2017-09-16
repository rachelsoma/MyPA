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

import java.lang.reflect.Method;

import static android.R.attr.value;

public class ViewFriendActivity extends MainFriendActivity {

    String fullName;
    String gender;
    int genderPosition;
    String age;
    String address;
    String suburb;
    String state;
    int statePosition;
    long friendPosition;
    String rowId;


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
                //todo: write this whole damn function to edit a record
               // Intent intent = (Intent)
                Snackbar.make(view, "Edit " + rowId, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent edit = new Intent(ViewFriendActivity.this, EditFriendActivity.class);
                edit.putExtras(getIntent().getExtras());
                startActivity(edit);
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: Add alert to confirm delete
                Snackbar.make(view, "Delete " + fullName, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                db.deleteFriend(rowId);
                Intent back = new Intent(ViewFriendActivity.this, MainFriendActivity.class);
                startActivity(back);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Calls method to get info from intent
        getFriendFromIntent();

        //passed info goes into text views
        TextView tvName = (TextView) findViewById(R.id.tvFullName);
        tvName.setText(fullName);
        TextView tvGender = (TextView) findViewById(R.id.tvGender);
        tvGender.setText(gender);
        TextView tvAge = (TextView) findViewById(R.id.tvAge);
        tvAge.setText(age);
        TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvAddress.setText(address);
        TextView tvSuburb = (TextView) findViewById(R.id.tvSuburb);
        tvSuburb.setText(suburb);
        TextView tvState = (TextView) findViewById(R.id.tvState);
        tvState.setText(state);
    }

    //Method to extract info passed to new activity and assign it to new variables
    public void getFriendFromIntent() {
        Bundle extras = getIntent().getExtras();
        fullName = extras.getString("extraName");
        gender = extras.getString("gender");
        genderPosition = extras.getInt("gender",gender.indexOf(gender));
        age = extras.getString("age");
        address = extras.getString("address");
        suburb = extras.getString("suburb");
        state = extras.getString("state");
        statePosition = extras.getInt("state",state.indexOf(state));
        friendPosition = extras.getLong("friendPosition");
        rowId = String.valueOf(friendPosition);
        //The key argument here must match the ones used in the activity intent came from


    }
}
