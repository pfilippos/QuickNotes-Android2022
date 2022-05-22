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
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Activitynotes extends AppCompatActivity { //Using androidX
    Toolbar toolbar;
    RecyclerView recyclerView;
    AdapterNoteClass adapter;
    List<AnoteClass> notes;
    NoteSimpleDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        toolbar = findViewById(R.id.toolbarnotes);
        setSupportActionBar(toolbar);

        db = new NoteSimpleDataBase(this);
        notes = db.getNotesList();

        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNoteClass(this,notes);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AnoteClass note = notes.get(position);
                Intent intent = new Intent(getApplicationContext(),ActivitynotesEdit.class);
                intent.putExtra("nTitle",note.getTitle());
                intent.putExtra("nContent",note.getText());
               // intent.putExtra("nId",note.getID());

                startActivity(intent);
                notes = db.getNotesList();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



    }
    //OnResume reload database db
    @Override
    protected void onResume() {
        super.onResume();
        notes = db.getNotesList();
        adapter.notifyDataSetChanged();
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
                Toast.makeText(this,"All notes cleared successfully",Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //When Back is pressed go to main activity refreshing it, NOT reloading it
    @Override
    public void onBackPressed() {
        Intent previous = new Intent(this,MainActivity.class);
        previous.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(previous);
        super.onBackPressed();
    }
}