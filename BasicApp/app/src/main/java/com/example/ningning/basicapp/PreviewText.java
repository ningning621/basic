package com.example.ningning.basicapp;
//import all classes???
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//this class is to give the user a preview of what text they entered in originally in the activity before
public class PreviewText extends Activity {
    //objects used in class
    Button cont; // the button the user presses to continue to the next activity
    Button backToEdit; // the button the user presses to go back and edit their text
    TextView tvText; // textbox that shows the user what they originally typed in
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_text);
        //takes text from last activity by using an intent
      /*  Intent intent = getIntent();
      //  Text text = (Text) intent.getSerializableExtra("text");
        //sets the tvText to the text from the previous activity
        tvText = (TextView)findViewById(R.id.tvMessage);
      //  tvText.setText(text.toString());
        // initializes the button
        cont = (Button)findViewById(R.id.saveButton);
        //once the button is pressed an intent is created to go to the home page activity
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(PreviewText.this, NotEmptyHomePage.class);
                startActivity(backHome);
            }
        });*/
        // initializes this button

        backToEdit = (Button)findViewById(R.id.back10);
        //once this button is pressed an intent is created to go to the TextInput class
        backToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTextInput = new Intent(PreviewText.this, TextInput.class);
                startActivity(toTextInput);
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
