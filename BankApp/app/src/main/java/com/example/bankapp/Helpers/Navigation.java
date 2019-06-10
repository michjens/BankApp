package com.example.bankapp.Helpers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bankapp.Activities.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankapp.Activities.Acc1Activity;
import com.example.bankapp.Activities.ProfileActivity;
import com.example.bankapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Navigation extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;
    private TextView acc1, acc2, acc3, acc4, acc5, acc1amount, acc2amount, acc3amount, acc4amount, acc5amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        init();
        navBottomHelper();











    }






    public void navBottomHelper() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerBottom
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_transfer:
                        Intent transfer = new Intent(getApplicationContext(), ProfileActivity.class);
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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerBottom);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

    public void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.drawer_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navEmail = headerView.findViewById(R.id.nav_useremail);

        navUsername.setText(currentUser.getDisplayName());
        navEmail.setText(currentUser.getEmail());


    }








    private void init() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        acc1 = findViewById(R.id.acc1);
       // acc1.setOnClickListener(this);
        acc2 = findViewById(R.id.acc2);
       // acc2.setOnClickListener(this);
        acc3 = findViewById(R.id.acc3);
       // acc3.setOnClickListener(this);
        acc4 = findViewById(R.id.acc4);
       // acc4.setOnClickListener(this);
        acc5 = findViewById(R.id.acc5);
       // acc5.setOnClickListener(this);
        acc1amount = findViewById(R.id.acc1amount);
      //  acc1amount.setOnClickListener(this);
        acc2amount = findViewById(R.id.acc2amount);
      //  acc2amount.setOnClickListener(this);
        acc3amount = findViewById(R.id.acc3amount);
      //  acc3amount.setOnClickListener(this);
        acc4amount = findViewById(R.id.acc4amount);
      //  acc4amount.setOnClickListener(this);
        acc5amount = findViewById(R.id.acc5amount);
      //  acc5amount.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
    }





}
