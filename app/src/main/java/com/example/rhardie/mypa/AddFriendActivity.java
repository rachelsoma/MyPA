package com.example.rhardie.mypa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFriendActivity extends AppCompatActivity {

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseManager(this);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button save = (Button) findViewById(R.id.saveNewFriend);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRec(view);
                Intent intent = new Intent(AddFriendActivity.this, MainFriendActivity.class);
                startActivity(intent);
            }
        });
        Button cancel = (Button) findViewById(R.id.cancelNewFriend);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFriendActivity.this, MainFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean insertRec(View view) {

        EditText fnameIn = (EditText) this.findViewById(R.id.tvFullName);
        EditText lnameIn = (EditText) this.findViewById(R.id.lName);
        EditText ageIn = (EditText) this.findViewById(R.id.tvAge);
        Spinner genderIn = (Spinner) this.findViewById(R.id.tvGender);
        EditText addressIn = (EditText) this.findViewById(R.id.tvAddress);
        EditText suburbIn = (EditText) this.findViewById(R.id.tvSuburb);
        Spinner stateIn = (Spinner) this.findViewById(R.id.stateSpinner);

        String fname = fnameIn.getText().toString();
        String lname = lnameIn.getText().toString();
        String tempyear = ageIn.getText().toString();
        int age = Integer.parseInt(tempyear);
        String gender = genderIn.getSelectedItem().toString();
        String address = addressIn.getText().toString();
        String suburb = suburbIn.getText().toString();
        String state = stateIn.getSelectedItem().toString();

        db.addFriend(fname, lname, gender, age, address, suburb, state);

        Toast.makeText(this, "Friend added", Toast.LENGTH_SHORT).show();

        //response.setText();
        //productRes.setText("");
        db.close();
        // makeInvisible();
        return true;
    }
}

