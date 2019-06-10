package com.example.bankapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bankapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.nav_view);
    }
}
