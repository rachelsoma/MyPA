package com.example.rhardie.mypa;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainTodoActivity extends BaseActivity {
    DatabaseManager db;
    ListView notComplete, complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todo);
        initToolbar();

        notComplete = (ListView) findViewById(R.id.toDoList);
        complete = (ListView) findViewById(R.id.toDoListComplete);
        db = new DatabaseManager(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddTodo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new To-Do item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(MainTodoActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
        showRec();
    }

    public void showRec() {
        notComplete.setAdapter(db.retrieveTodo(this, 0));
        complete.setAdapter(db.retrieveTodo(this, 1));

        notComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) notComplete.getItemAtPosition(position);
                int complete =
                        cursor.getInt(cursor.getColumnIndex(TableManager.TODO_COL_COMPLETE));
                db.toggleTodo(cursor.getLong(cursor.getColumnIndex("_id")), complete);
                Intent intent = new Intent(MainTodoActivity.this, MainTodoActivity.class);
                startActivity(intent);
            }
        });

        complete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) complete.getItemAtPosition(position);
                int complete =
                        cursor.getInt(cursor.getColumnIndex(TableManager.TODO_COL_COMPLETE));
                db.toggleTodo(cursor.getLong(cursor.getColumnIndex("_id")), complete);
                Intent intent = new Intent(MainTodoActivity.this, MainTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}
