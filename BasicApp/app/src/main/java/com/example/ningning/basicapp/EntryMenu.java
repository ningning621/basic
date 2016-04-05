// Name: Lesley Huang and Trishla Pokharna
// Program file: EntryMenu.class
// Class Description: This activity allows the user to enter in how many hours of
// exercise they have done, how many hours they have slept, and their level of happiness
package com.example.ningning.basicapp;
//imports all necessary classes
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Typeface;
import android.view.View.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class EntryMenu extends Activity {
    //declaring textview and button objects
    TextView text;
    TextView ratings;
    TextView mTitle;
    Button toTextInput;
    Button toBack;
    Button toRatingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_menu);

        //initializes all objects
        text = (TextView)findViewById(R.id.addText);
        ratings = (TextView)findViewById(R.id.addRatings);
        mTitle = (TextView)findViewById(R.id.menutitle);

        //changes font on page
        Typeface francois = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        text.setTypeface(francois);
        ratings.setTypeface(francois);

        Typeface sc = Typeface.createFromAsset(getAssets(), "fonts/AmaticSC-Bold.ttf");
        mTitle.setTypeface(sc);


        //when this button is pressed, an intent is created that starts the TextInput class
        toTextInput = (Button)findViewById(R.id.addText);
        toTextInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(EntryMenu.this, TextInput.class);
                startActivity(intent4);
            }
        });

        //when this button is pressed, an intent is created that starts the NotEmptyHomePage class
        toBack = (Button)findViewById(R.id.back3);
        toBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(EntryMenu.this, NotEmptyHomePage.class);
                startActivity(intent5);
            }
        });

        //when this button is pressed, an intent is created that starts the RatingInput class
        toRatingInput = (Button)findViewById(R.id.addRatings);
        toRatingInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wow = new Intent(EntryMenu.this, RatingInput.class);
                startActivity(wow);
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
