// Name: Lesley Huang and Trishla Pokharna
// Program file: TextInput.java
// Class Description: This class allows the user to enter in text that will eventually be put into an entry
package com.example.ningning.basicapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class TextInput extends Activity {
    Button cont;
    Button backHomeBtn;
    EditText txt;
    EntryRepo repo;
    Entries newEntry;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        txt = (EditText)findViewById(R.id.editText1);
        repo = new EntryRepo(this);
        cont = (Button) findViewById(R.id.button3);

        //when button is pressed
        cont.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview = new Intent(TextInput.this, NotEmptyHomePage.class);

                //get calendar time from android app
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy");
                String  formattedDate = df.format(c.getTime());

                List<Entries>entriesList = repo.getAllEntriesList();

                //if list is empty, add entry to list
                if (entriesList.size()==0) {
                    newEntry = new Entries(formattedDate, txt.getText().toString(), " ");
                    //newEntry.setText();
                    repo.insert(newEntry);
                }

                //if entry date is different from previous, add entry, else update entry
                for (int i = entriesList.size()-1; i >entriesList.size()-2; i--){
                    if (entriesList.get(i).getDate().equals(formattedDate)) {
                        newEntry = entriesList.get(i);
                        newEntry.setText(txt.getText().toString());
                        repo.update(newEntry);
                    }
                    else {
                        newEntry = new Entries(formattedDate, txt.getText().toString(), " ");
                        //newEntry.setText();
                        repo.insert(newEntry);
                    }
                }

                startActivity(preview);

            }
        });

        //return home
        backHomeBtn = (Button)findViewById(R.id.back4);
        backHomeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(TextInput.this, NotEmptyHomePage.class);
                startActivity(toHome);
            }
        });

        //set fonts
        Typeface sc = Typeface.createFromAsset(getAssets(), "fonts/AmaticSC-Bold.ttf");
        txt1 = (TextView)findViewById(R.id.tap);
        txt1.setTypeface(sc);

        Typeface fr = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        cont.setTypeface(fr);

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
