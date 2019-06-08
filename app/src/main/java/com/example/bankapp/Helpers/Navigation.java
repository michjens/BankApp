package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bankapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private TextView acc1, acc2, acc3, acc4, acc5, acc1amount, acc2amount, acc3amount, acc4amount, acc5amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);




        init();
        navHelpers(toolbar, drawer);

    }

    public void navHelpers(Toolbar toolbar, DrawerLayout drawer) {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Navigation/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent home = new Intent(getApplicationContext(), Navigation.class);
            startActivity(home);
        } else if (id == R.id.nav_profile) {
            Intent edit_profile = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(edit_profile);
        } else if (id == R.id.nav_settings) {
         //   getSupportFragmentManager().beginTransaction().replace(R.id.frameLay, new SettingsFragment()).commit();
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    public void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navEmail = headerView.findViewById(R.id.nav_useremail);

        navUsername.setText(currentUser.getDisplayName());
        navEmail.setText(currentUser.getEmail());


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.acc1:
            case R.id.acc1amount:
                Intent acc1 = new Intent(getApplicationContext(), Acc1Activity.class);
                startActivity(acc1);
                break;
            case R.id.acc2:
            case R.id.acc2amount:
                Intent acc2 = new Intent(getApplicationContext(), Acc1Activity.class);
                startActivity(acc2);
                break;
            case R.id.acc3:
            case R.id.acc3amount:
                Intent acc3 = new Intent(getApplicationContext(), Acc1Activity.class);
                startActivity(acc3);
                break;
            case R.id.acc4:
            case R.id.acc4amount:
                Intent acc4 = new Intent(getApplicationContext(), Acc1Activity.class);
                startActivity(acc4);
                break;
            case R.id.acc5:
            case R.id.acc5amount:
                Intent acc5 = new Intent(getApplicationContext(), Acc1Activity.class);
                startActivity(acc5);
                break;



        }
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        acc1 = findViewById(R.id.acc1);
        acc1.setOnClickListener(this);
        acc2 = findViewById(R.id.acc2);
        acc2.setOnClickListener(this);
        acc3 = findViewById(R.id.acc3);
        acc3.setOnClickListener(this);
        acc4 = findViewById(R.id.acc4);
        acc4.setOnClickListener(this);
        acc5 = findViewById(R.id.acc5);
        acc5.setOnClickListener(this);
        acc1amount = findViewById(R.id.acc1amount);
        acc1amount.setOnClickListener(this);
        acc2amount = findViewById(R.id.acc2amount);
        acc2amount.setOnClickListener(this);
        acc3amount = findViewById(R.id.acc3amount);
        acc3amount.setOnClickListener(this);
        acc4amount = findViewById(R.id.acc4amount);
        acc4amount.setOnClickListener(this);
        acc5amount = findViewById(R.id.acc5amount);
        acc5amount.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
    }
}
