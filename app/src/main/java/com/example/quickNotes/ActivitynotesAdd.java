package com.example.quickNotes;

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

public class ActivitynotesAdd extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteText;
    String theDate;
    String theTime;
    Calendar calendar;


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
        setContentView(R.layout.activity_activitynotes_add);

        toolbar = findViewById(R.id.toolbarnotes);
        setSupportActionBar(toolbar);


        noteTitle = findViewById(R.id.noteTitle);
        noteText = findViewById(R.id.noteText);

        getSupportActionBar().setTitle("AddNote - Notes - new note");

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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
        theDate =calendar.get(Calendar.DAY_OF_MONTH)+ "/" + (calendar.get(Calendar.MONTH)+1)+ "/" +calendar.get(Calendar.YEAR); // D/M/Y
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

        switch (item.getItemId()){
            case R.id.Save:
                Note note = new Note(noteTitle.getText().toString(),noteText.getText().toString(),theDate,theTime); //create a knew note without id
                SQLiteDB db = new SQLiteDB(this);
                db.NoteaddFunc(note); //Adds note object to database
                Toast.makeText(this,"Note saved successfully",Toast.LENGTH_SHORT).show();
                onBackPressed();
                Intent previous = new Intent(this,Activitynotes.class);
                previous.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(previous);
                break;
            case R.id.del:
                Toast.makeText(this,"Note Discarded",Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}