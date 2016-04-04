// Name: Lesley Huang and Trishla Pokharna
// Program file: EntryActiivity.java
// Class Description: This class copies all the information from the entry previously
// made into a new entry that can be picked when they are added to the home page
package com.example.ningning.basicapp;
import android.graphics.Typeface;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EntryActiivity extends Activity implements View.OnClickListener {
    TextView Edate;
    TextView Etext;
    TextView Erating;
    Button update;
    Button delete;
    Button close;
    DBHelper db;
    EditText entryDate;
    EditText entryText;
    EditText entryRating;
    Entries selectedEntry;
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    private int Entries_id = 0;
    private static final String TAG = EntryActiivity.class.getSimpleName();


    //when this activity is created the button, EditText, and other objects are initialized
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_actiivity);

        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        close = (Button) findViewById(R.id.close);


        entryText = (EditText) findViewById(R.id.textEdit);
        entryRating = (EditText) findViewById(R.id.ratingEdit);

        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        close.setOnClickListener(this);

        Entries_id = 0;

        //this gets the intent from the previous activity that the user was in
        Intent intent = getIntent();

        // this gets the id of the Entry through an intent
        int Entries_id = intent.getIntExtra("entry_id", 0);

        //this creates an EntryRepo object
        EntryRepo repo = new EntryRepo(this);

        //this creates an Entry object
        Entries entry = new Entries();

        //this sets the entry object to be the repo object’s id
        entry = repo.getEntryById(Entries_id);
        //this sets the selected entry to be the entry
        selectedEntry = entry;

        //this initializes all the TextViews that are in the activity
        Edate = (TextView)findViewById(R.id.Edate);
        Etext = (TextView)findViewById(R.id.Etext);
        Erating = (TextView)findViewById(R.id.Erating);

        //it sets the date to be what is int the entry that was created by the user
        Edate.setText(entry.getDate());

        //it sets the text of events to be what is int the entry that was created by the user
        Etext.setText(entry.getText());

        //it sets the text of ratings to be what is int the entry that was created by the user
        Erating.setText(entry.getRating());

        //changes font of text on page
        Typeface fr = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        one = (TextView)findViewById(R.id.dateLabel);
        two = (TextView)findViewById(R.id.textLabel);
        three = (TextView)findViewById(R.id.ratingLabel);
        four = (TextView)findViewById(R.id.textEditLabel);
        five = (TextView)findViewById(R.id.ratingEditLabel);

        one.setTypeface(fr);
        two.setTypeface(fr);
        three.setTypeface(fr);
        four.setTypeface(fr);
        five.setTypeface(fr);
    }

    //default method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_actiivity, menu);
        return true;
    }

    //default method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //once the button update is pressed it either updates or deletes an entry
    public void onClick(View v) {
        if (v == findViewById(R.id.update)) {
            EntryRepo repo = new EntryRepo(this);

            //it sets the entry text, and rating to be what the user entered in the previous activities
            selectedEntry.setText(entryText.getText().toString());
            selectedEntry.setRating(entryRating.getText().toString());

            repo.update(selectedEntry);

            //every time an entry is made, a blurb in the bottom says New Entry Made
            Toast.makeText(getApplicationContext(), "Entry Updated!", Toast.LENGTH_SHORT).show();

            // if the delete button is presses
            finish();
            // if the delete button is presses
        }else if (v ==findViewById(R.id.delete)){

            //an EntryRepo class is made that deletes the selected entry
            EntryRepo repo = new EntryRepo(this);
            repo.delete(selectedEntry);

            //says entry deleted
            Toast.makeText(getApplicationContext(), "Entry Deleted!", Toast.LENGTH_SHORT).show();
            //closes everything after the entry is deleted
            finish();
        }
        else if(v== findViewById(R.id.close)){
            // if the close button is finished it closes everything on the activity and returns to the previous home page
            finish();
        }
    }
}
