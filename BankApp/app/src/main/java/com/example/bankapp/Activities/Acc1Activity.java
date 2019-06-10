package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bankapp.Helpers.Navigation;
import com.example.bankapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Acc1Activity extends Navigation {



    TextView balance, trans1, trans2, trans3, trans4, trans5, trans6, trans7, trans8, trans9, trans10, trans11, trans12, trans13, trans14, trans15, trans16, trans17, trans18, trans19, trans20;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc1);
        init();
        loadData();


        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerBottom
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_transfer:
                        Intent transfer = new Intent(getApplicationContext(), TransferActivity.class);
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
                        Intent menu = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(menu);
                        return true;

                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerBottom);

    }

    private void loadData() {
        final DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName() + "/Accounts/Budget");
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Double balanceAmount = 0.0;
                    Double getData;
                    for (int i = 1; i <= 21; i++) {

                        String viewName = "trans" + i;
                        int id = getResources().getIdentifier(viewName, "id", getPackageName());
                        if (id != 0) {
                            TextView textTest = findViewById(id);
                            getData = documentSnapshot.getDouble("trans" + i);


                            textTest.setText(getData.toString());
                            balanceAmount += getData;
                        }
                    }
                    balanceAmount = (double) Math.round(balanceAmount*100/100);
                    balance.setText(balanceAmount.toString());
                    Map<String, Object> balanceSave = new HashMap<>();
                    balanceSave.put("balance", balanceAmount);
                    mDocRef.update(balanceSave);

                }
            }
        });

    }

    private void init() {
        bottomNavigationView = findViewById(R.id.nav_view);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        balance = findViewById(R.id.balance);
        trans1 = findViewById(R.id.trans1);
        trans2 = findViewById(R.id.trans2);
        trans3 = findViewById(R.id.trans3);
        trans4 = findViewById(R.id.trans4);
        trans5 = findViewById(R.id.trans5);
        trans6 = findViewById(R.id.trans6);
        trans7 = findViewById(R.id.trans7);
        trans8 = findViewById(R.id.trans8);
        trans9 = findViewById(R.id.trans9);
        trans10 = findViewById(R.id.trans10);
        trans11 = findViewById(R.id.trans11);
        trans12 = findViewById(R.id.trans12);
        trans13 = findViewById(R.id.trans13);
        trans14 = findViewById(R.id.trans14);
        trans15 = findViewById(R.id.trans15);
        trans16 = findViewById(R.id.trans16);
        trans17 = findViewById(R.id.trans17);
        trans18 = findViewById(R.id.trans18);
        trans19 = findViewById(R.id.trans19);
        trans20 = findViewById(R.id.trans20);


    }
}
