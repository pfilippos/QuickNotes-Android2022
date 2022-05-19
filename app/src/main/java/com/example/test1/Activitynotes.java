package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Activitynotes extends AppCompatActivity { //Using androidX
    Toolbar toolbar;
    RecyclerView recyclerView;
    AdapterNoteClass adapter;
    List<AnoteClass> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        toolbar = findViewById(R.id.toolbarnotes);
        setSupportActionBar(toolbar);

        NoteSimpleDataBase db = new NoteSimpleDataBase(this);
        notes = db.getNotesList();

        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNoteClass(this,notes);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notesfunctionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(this,ActivitynotesAdd.class);
                startActivity(intent);
                break;
            case R.id.ClearAll: // !!!! delete != del
                NoteSimpleDataBase db = new NoteSimpleDataBase(this);
                db.ClearAllNotes("notesDbTable");
                //Toast.makeText(this,"Min to patas tzampa den kanei kati",Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}