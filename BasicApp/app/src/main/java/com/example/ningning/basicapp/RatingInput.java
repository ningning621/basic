// Name: Lesley Huang and Trishla Pokharna
// Program file: RatingInput.java
// Class Description: This class allows the user to enter in ratings for happiness, exercise, and sleep that will eventually be put into an entry
package com.example.ningning.basicapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Rating;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class RatingInput extends Activity {
    Button cont;
    Button backHomeBtn;
    EditText etH;
    EditText etS;
    EditText etE;
    EntryRepo repo;
    Entries newEntry;
    String eTH;
    String eTS;
    String eTE;
    String average;
    TextView txt;
    TextView one;
    TextView two;
    TextView three;
    private static final String TAG = RatingInput.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_input);
        etH = (EditText)findViewById(R.id.eTLevel);
        etS = (EditText)findViewById(R.id.eTsleep);
        etE = (EditText)findViewById(R.id.eTexercise);

        //set EditView to string
        eTH = etH.getText().toString();
        eTS = etS.getText().toString();
        eTE = etE.getText().toString();
        repo = new EntryRepo(this);

        //when button is pressed
        cont = (Button) findViewById(R.id.sub);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview = new Intent(RatingInput.this, NotEmptyHomePage.class);

                //get current time from android phone
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
                String  formattedDate = df.format(c.getTime());

                List<Entries> entriesList = repo.getAllEntriesList();

                //turn to final string
                average = "Happiness= "+ etH.getText().toString() + "\nSleep= " + etS.getText().toString() + "\nExercise= "+ etE.getText().toString();

                //if there's nothing in the list, add entry to list
                if (entriesList.size()==0) {
                    newEntry = new Entries(formattedDate, average, " ");
                    //newEntry.setText();
                    repo.insert(newEntry);
                }

                //if entry date is different from previous date, add to list, else update to entries
                for (int i = entriesList.size()-1; i >entriesList.size()-2; i--){
                    if (entriesList.get(i).getDate().equals(formattedDate)) {
                        newEntry = entriesList.get(i);
                        newEntry.setRating(average);
                        repo.update(newEntry);
                        break;
                    }
                    else {
                        newEntry = new Entries(formattedDate, " ",average);
                        //newEntry.setText();
                        repo.insert(newEntry);
                       break;
                    }
                }

                startActivity(preview);

            }
        });

        //button to return to home
        backHomeBtn = (Button)findViewById(R.id.back12);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(RatingInput.this, NotEmptyHomePage.class);
                startActivity(toHome);
            }
        });

        //set fonts
        Typeface sc = Typeface.createFromAsset(getAssets(), "fonts/AmaticSC-Bold.ttf");
        txt = (TextView)findViewById(R.id.title);
        txt.setTypeface(sc);

        Typeface fr = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        one = (TextView)findViewById(R.id.textView);
        two = (TextView)findViewById(R.id.exercise1);
        three = (TextView)findViewById(R.id.sleep);

        one.setTypeface(fr);
        two.setTypeface(fr);
        three.setTypeface(fr);
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
