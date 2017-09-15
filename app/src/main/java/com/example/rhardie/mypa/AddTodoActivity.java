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
import android.widget.Toast;

public class AddTodoActivity extends AppCompatActivity {
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseManager(this);

        Button save = (Button) findViewById(R.id.saveNewTodo);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRec(view);
                Intent intent = new Intent(AddTodoActivity.this, MainTodoActivity.class);
                startActivity(intent);
            }
        });
        Button cancel = (Button) findViewById(R.id.cancelNewTodo);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTodoActivity.this, MainTodoActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean insertRec(View view) {

        EditText nameIn = (EditText) this.findViewById(R.id.todoName);

        EditText locationIn = (EditText) this.findViewById(R.id.todoLocation);

        String todoName = nameIn.getText().toString();
        String todoLocation = locationIn.getText().toString();

        //todo: Validate data before adding to database
        db.addTodo(todoName,todoLocation);

        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();

        //response.setText();
        //productRes.setText("");
        db.close();
        // makeInvisible();
        return true;
    }
}
