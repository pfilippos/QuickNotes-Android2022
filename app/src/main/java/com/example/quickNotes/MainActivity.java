package com.example.quickNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView tvAreThereNotes, tvSub;
    List<Note> noteList;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            System.out.println(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open,R.string.menu_Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gteal, null)));



        //Checks to see if there are any notes available, if not shows message and arrow!
        tvAreThereNotes = findViewById(R.id.aretherenotes);
        tvSub = findViewById(R.id.textView9);

        SQLiteDB db = new SQLiteDB(this);
        noteList = db.getNotesList();
        if(noteList.isEmpty()){
            tvSub.setText("Touch the menu to add a note!");
            tvAreThereNotes.setText("You have no notes yet!");
        }
        else if(noteList.size() == 1){
            tvAreThereNotes.setText("You have 1 note!");

        }
        else{
            tvAreThereNotes.setText("You have " + noteList.size()+" notes!");
        }


        //Listener for the draw Menu on the home screen
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Homemenu:
                        openHomeScreen();
                        break;

                    case R.id.Notesmenu:
                        openActivityNotes();
                        break;



                    case R.id.Aboutusmenu:
                        openAboutus();
                        break;

                    case R.id.Infomenu:
                        openAppinfo();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    //These just open the corresponding Activity
    public void openActivityNotes(){
        Intent intent = new Intent(this, Activitynotes.class);
        startActivity(intent);
    }

    public void openHomeScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openAboutus(){
        Intent intent = new Intent(this,ActivityAboutUs.class);
        startActivity(intent);
    }

    public void openAppinfo(){
        Intent intent = new Intent(this,ActivityAppinfo.class);
        startActivity(intent);
    }


}