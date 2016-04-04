package com.example.ningning.basicapp;

import android.content.Intent;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HomePage extends Activity {
    Button backLoginBtn;
    Button addEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        backLoginBtn =  (Button)findViewById(R.id.back);
        addEntry = (Button)findViewById(R.id.add);
        backLoginBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent backLogin = new Intent(HomePage.this, LoginPage.class);
                startActivity(backLogin);
            }
        });

        addEntry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(HomePage.this, EntryMenu.class);
                startActivity(toMenu);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
