package com.example.bankapp.Helpers;

import android.content.Intent;
import android.os.Bundle;

import com.example.bankapp.Activities.HomeActivity;
import com.example.bankapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;

public class BottomNavHelper extends Navigation {


    Toolbar toolbar;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_helper_home);
        init();

        navBottomHelper();






    }


    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
    }

    public void navBottomHelper() {

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_transfer:
                    Intent transfer = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(transfer);
                    return true;
               /* case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };



}
