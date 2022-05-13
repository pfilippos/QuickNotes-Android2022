package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;


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

                    case R.id.Paintmenu:
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