package com.example.bankapp.Activities;

import android.content.Intent;

import com.example.bankapp.Helpers.BottomNavHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bankapp.Helpers.Navigation;
import com.example.bankapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HomeActivity extends Navigation implements View.OnClickListener {

    private TextView acc1, acc2, acc3, acc4, acc5, acc1amount, acc2amount, acc3amount, acc4amount, acc5amount;
    Toolbar toolbar, bottom_nav;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    ArrayList accountNames, accountAmounts, arrayNames;
    HashMap activeAccounts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        loadActiveAccounts();

        for(int i = 0; i < arrayNames.size(); i++){
            loadAccountBalance(arrayNames.get(i).toString());
        }


        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerBottom
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_transfer:
                        Intent transfer = new Intent(getApplicationContext(), TransferActivity.class);
                        transfer.putExtra("activeAcc", activeAccounts);
                        startActivity(transfer);
                        return true;
                    case R.id.nav_bills:
                        Intent bills = new Intent(getApplicationContext(), BillsActivity.class);
                        startActivity(bills);
                        return true;
                    case R.id.nav_home:
                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                        return true;
                    case R.id.nav_menu:
                        Intent menu = new Intent(getApplicationContext(), SettingsActivity.class);
                        menu.putExtra("activeAcc", activeAccounts);
                        startActivity(menu);
                        return true;

                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerBottom);

    }

    private void loadAccountBalance(final String accountName) {

            final DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName() + "/Accounts/" + accountName);
            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        if(accountName.equals("Budget")){
                            acc1amount.setText(documentSnapshot.getDouble("balance").toString());
                        }else if(accountName.equals("Default")){
                            acc2amount.setText(documentSnapshot.getDouble("balance").toString());
                        }else if(accountName.equals("Business")){
                            acc3amount.setText(documentSnapshot.getDouble("balance").toString());
                        }else if(accountName.equals("Savings")){
                            acc4amount.setText(documentSnapshot.getDouble("balance").toString());
                        }else if(accountName.equals("Pension"))
                            acc5amount.setText(documentSnapshot.getDouble("balance").toString());

                    }}

            });
        }


    private void loadActiveAccounts() {

        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName());
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    for (int j = 0; j < accountNames.size(); j++) {
                        TextView accViews = (TextView) accountNames.get(j);
                        TextView accAmountViews = (TextView) accountAmounts.get(j);
                        if (documentSnapshot.getString(arrayNames.get(j).toString()).equalsIgnoreCase("false")) {

                            accViews.setVisibility(View.INVISIBLE);
                            accAmountViews.setVisibility(View.INVISIBLE);

                        } else {
                            accViews.setVisibility(View.VISIBLE);
                            accAmountViews.setVisibility(View.VISIBLE);
                        }

                        activeAccounts.put(arrayNames.get(j).toString(), documentSnapshot.getString(arrayNames.get(j).toString()));
                    }
                }
            }
        });


    }


    private void init() {
        accountNames = new ArrayList<>(Arrays.asList(acc1 = findViewById(R.id.acc1), acc2 = findViewById(R.id.acc2), acc3 = findViewById(R.id.acc3), acc4 = findViewById(R.id.acc4), acc5 = findViewById(R.id.acc5)));
        accountAmounts = new ArrayList<>(Arrays.asList(acc1amount = findViewById(R.id.acc1amount),acc2amount = findViewById(R.id.acc2amount), acc3amount = findViewById(R.id.acc3amount), acc4amount = findViewById(R.id.acc4amount), acc5amount = findViewById(R.id.acc5amount)));
        arrayNames = new ArrayList<String>();
        activeAccounts = new HashMap<String, String>();
        arrayNames.add(acc1.getText().toString());
        arrayNames.add(acc2.getText().toString());
        arrayNames.add(acc3.getText().toString());
        arrayNames.add(acc4.getText().toString());
        arrayNames.add(acc5.getText().toString());
        acc1.setOnClickListener(this);
        acc2.setOnClickListener(this);
        acc3.setOnClickListener(this);
        acc4.setOnClickListener(this);
        acc5.setOnClickListener(this);
        acc1amount.setOnClickListener(this);
        acc2amount.setOnClickListener(this);
        acc3amount.setOnClickListener(this);
        acc4amount.setOnClickListener(this);
        acc5amount.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        bottom_nav = findViewById(R.id.toolbarBottom);
        bottomNavigationView = findViewById(R.id.nav_view);
        mAuth = FirebaseAuth.getInstance();

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
}
