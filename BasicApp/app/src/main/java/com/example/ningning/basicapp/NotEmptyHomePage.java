// Name: Lesley Huang and Trishla Pokharna
// Program file:  NotEmptyHomePage.java
// Class Description: This class is the activity that is the home page that contains all the entries entered by the user.
package com.example.ningning.basicapp;
//imports all necessary classes
import android.graphics.Typeface;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

// this class has to extend the ListActivity class and implement the interface View.OnClickListener
public class NotEmptyHomePage extends ListActivity implements View.OnClickListener {
    Button add, getAll;
    TextView entry_id;
    private static final String TAG = NotEmptyHomePage.class.getSimpleName();
    Entries toAdd = new Entries();
    ContentValues value = new ContentValues();
    List<Entries> list;
    DBHelper db = new DBHelper(this);
    ArrayAdapter <String> myAdapter;
    Text txt = new Text();
    TextView instructions;
    Button b;

    //once the add button is pressed, the user goes to the EntryMenu.class so that they can add their entries
    @Override
    //if the add button is pressed an intent is created to go to the EntryMenu.class or
    public void onClick(View v) {
        if (v== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this, EntryMenu.class);

            //sends information to the next activity (EntryMenu.class) of the id of the entry object
            intent.putExtra("entry_id",0);

            startActivity(intent);


        }else {
            //declare and instantiates an EntryRepo object that is a class that was previously made that has methods for the database
            EntryRepo repo = new EntryRepo(this);

            //this creates a collection of strings that contains entries
            ArrayList<HashMap<String, String>> entriesList =  repo.getEntriesList();

            if(entriesList.size()!=0) {
                ListView lv = getListView();
                //once entries are made by the user
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        entry_id = (TextView) view.findViewById(R.id.entry_Id);
                        String entryId = entry_id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),EntryActiivity.class);
                        // it sends the entry_id to the EntryActiviity.class and converts the entry_id into an Integer
                        objIndent.putExtra("entry_id", Integer.parseInt(entryId));
                        startActivity(objIndent);
                    }
                });
                //used to display listView of Entries
                ListAdapter adapter = new SimpleAdapter( NotEmptyHomePage.this,entriesList,
                        R.layout.view_entry_info, new String[] { "id","date"},
                        new int[] {R.id.entry_Id, R.id.entry_date});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this, "No entry!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_empty_home_page);

        add = (Button)findViewById(R.id.btnAdd);
        add.setOnClickListener(this);

        getAll = (Button)findViewById(R.id.btnGetAll);
        getAll.setOnClickListener(this);

        Typeface francois = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        instructions = (TextView)findViewById(R.id.textView3);
        instructions.setTypeface(francois);

        b = (Button)findViewById(R.id.btnGetAll);
        b.setTypeface(francois);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_not_empty_home_page, menu);
        return true;
    }

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
}

