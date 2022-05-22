package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ActivitynotesEdit extends AppCompatActivity {
    Toolbar toolbar;
    String noteTitle;
    String noteText;
   // int noteID;
    EditText enoteTitle, enoteText;
    String theDate;
    String theTime;
    Calendar calendar;
    NoteSimpleDataBase db;


    private String correct(int i) {
        if(i>=10){
            return String.valueOf(i);

        }
        else
            return "0"+i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_edit);
        db = new NoteSimpleDataBase(this);


        toolbar = findViewById(R.id.toolbarnotes);
        setSupportActionBar(toolbar);


        //get caller intent data
        Intent caller = getIntent();
       // noteID = caller.getIntExtra("nID", 0);

        noteTitle = caller.getStringExtra("nTitle");
        noteText = caller.getStringExtra("nContent");

        enoteTitle = findViewById(R.id.noteTitle);
        enoteText = findViewById(R.id.noteText);
        enoteTitle.setText(noteTitle);
        enoteText.setText(noteText);



        getSupportActionBar().setTitle("Edit Note");

        enoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getSupportActionBar().setTitle(charSequence);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()!=0){
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        calendar=Calendar.getInstance();
        theDate =calendar.get(Calendar.YEAR)+ "/" + (calendar.get(Calendar.MONTH)+1)+ "/" + calendar.get(Calendar.DAY_OF_MONTH); // D/M/Y
        theTime=correct(calendar.get(Calendar.HOUR))+ ":" + correct(calendar.get(Calendar.MINUTE));

    }

    @Override //Almost the same as from Activitynotes
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.noteoptionsmenu,menu);
        return true;
    }

    @Override//Almost the same as from Activitynotes but with Save and del !!
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AnoteClass note = new AnoteClass(enoteTitle.getText().toString(),enoteText.getText().toString(),theDate,theTime); //create a knew note without id
        Intent previous = new Intent(this,Activitynotes.class);

        switch (item.getItemId()){
            case R.id.Save:

                db.NoteupdFunc(note); //Updates note object to database
                Toast.makeText(this,"Note saved successfully",Toast.LENGTH_SHORT).show();
                previous.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(previous);
                break;
            case R.id.del:
                db.NotedelFunc(note);
                Toast.makeText(this,"Note Discarded",Toast.LENGTH_SHORT).show();
                previous.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(previous);
                break;
        }

        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}