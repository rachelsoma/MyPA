package com.example.rhardie.mypa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.RowId;

public class EditFriendActivity extends AppCompatActivity {

    DatabaseManager db;

    String fName;
    String lName;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button save = (Button) findViewById(R.id.saveEditFriend);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFriend(view, rowId);
                Intent intent = new Intent(EditFriendActivity.this, MainFriendActivity.class);
                startActivity(intent);
            }
        });
        Button cancel = (Button) findViewById(R.id.cancelEditFriend);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditFriendActivity.this, MainFriendActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFriendFromIntent();

        //passed info goes into text views

        EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        etFirstName.setText(fName, TextView.BufferType.EDITABLE);
        EditText etLastName = (EditText) findViewById(R.id.etLastName);
        etLastName.setText(lName, TextView.BufferType.EDITABLE);
        Spinner etGender = (Spinner) findViewById(R.id.etGender);
        etGender.getItemAtPosition(genderPosition);
        EditText etAge = (EditText) findViewById(R.id.etAge);
        etAge.setText(age, TextView.BufferType.EDITABLE);
        EditText etAddress = (EditText) findViewById(R.id.etAddress);
        etAddress.setText(address, TextView.BufferType.EDITABLE);
        EditText etSuburb = (EditText) findViewById(R.id.etSuburb);
        etSuburb.setText(suburb, TextView.BufferType.EDITABLE);
        Spinner etState = (Spinner) findViewById(R.id.etState);
        etState.getItemAtPosition(statePosition);
    }


    //passed info goes into text views


    //Method to extract info passed to new activity and assign it to new variables
    public void getFriendFromIntent() {
        Bundle extras = getIntent().getExtras();
        //fullName = extras.getString("extraName");
        fName = extras.getString("extraFname");
        lName = extras.getString("extraLname");
        gender = extras.getString("gender");
        genderPosition = extras.getInt("genderPosition");
        age = extras.getString("age");
        address = extras.getString("address");
        suburb = extras.getString("suburb");
        state = extras.getString("state");
        statePosition = extras.getInt("statePosition");
        friendPosition = extras.getLong("friendPosition");
        rowId = String.valueOf(extras.getLong("friendPosition"));
        //The key argument here must match the ones used in the activity intent came from


    }


    public boolean  updateFriend(View view, String rowId) {

        EditText fnameIn = (EditText) this.findViewById(R.id.etFirstName);
        EditText lnameIn = (EditText) this.findViewById(R.id.etLastName);
        EditText ageIn = (EditText) this.findViewById(R.id.etAge);
        Spinner genderIn = (Spinner) this.findViewById(R.id.etGender);
        EditText addressIn = (EditText) this.findViewById(R.id.etAddress);
        EditText suburbIn = (EditText) this.findViewById(R.id.etSuburb);
        Spinner stateIn = (Spinner) this.findViewById(R.id.etState);

        String fname = fnameIn.getText().toString();
        String lname = lnameIn.getText().toString();
        String tempyear = ageIn.getText().toString();
        int age = Integer.parseInt(tempyear);
        String gender = genderIn.getSelectedItem().toString();
        String address = addressIn.getText().toString();
        String suburb = suburbIn.getText().toString();
        String state = stateIn.getSelectedItem().toString();
        //todo: Validate data before adding to database
        db.updateFriend(rowId,fname, lname, gender, age, address, suburb, state);

        Toast.makeText(this, "Friend added", Toast.LENGTH_SHORT).show();

        //response.setText();
        //productRes.setText("");
        db.close();
        // makeInvisible();
        return true;
    }
}