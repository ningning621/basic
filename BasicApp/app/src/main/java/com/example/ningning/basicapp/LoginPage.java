// Name: Lesley Huang and Trishla Pokharna
// Program file: LoginPage.class
// Class Description: This activity introduces the user to the app and allows the user to tap a button to enter the app

package com.example.ningning.basicapp;

//import all necessary classes

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.TextView;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginPage extends Activity implements OnClickListener{
    //declares all necessary objects: a button and two textviews
    Button loginBtn;
    TextView title;
    TextView description;

    @Override
        //Once this activity is opened, this method is automatically called. It initializes all the objects //first declared
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //initializes the button object
        loginBtn =  (Button)findViewById(R.id.but);
        loginBtn.setOnClickListener(this);

        //initializes the textview object
        title = (TextView) findViewById(R.id.loginName);

        //this changes the font of the text in the title TextView
        Typeface sc = Typeface.createFromAsset(getAssets(), "fonts/AmaticSC-Bold.ttf");
        title.setTypeface(sc);

        //this initializes the description TextView object
        description = (TextView) findViewById(R.id.loginDescription);

        //this changes the font of the description TextView object
        Typeface francois = Typeface.createFromAsset(getAssets(), "fonts/FrancoisOne.ttf");
        description.setTypeface(francois);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    //after the button object is pressed, an intent is created to have the user go to the next activity, //the NOTEMPTYHOMEPAGE.CLASS<--have to change this
    @Override
    public void onClick(View v) {
        Intent toHome = new Intent(this, NotEmptyHomePage.class);
        startActivity(toHome);
    }
}


