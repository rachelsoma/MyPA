package com.example.rhardie.mypa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Rachel Hardie on 10/09/2017.
 * 300960 (Spring 2017) Mobile Applications Development
 * Kingswood, Thursday 1200
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.friendMenu) {
            Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainFriendActivity.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.todoMenu) {
            Toast.makeText(this, "To-do", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainTodoActivity.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.eventMenu) {
            Toast.makeText(this, "Events", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainEventActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}