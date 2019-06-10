package com.example.bankapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bankapp.Helpers.Navigation;
import com.example.bankapp.Helpers.Utility;
import com.example.bankapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransferActivity extends Navigation {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    BottomNavigationView bottomNavigationView;
    Spinner fromSpinner, toSpinner, whenSpinner;
    ArrayList listFrom, listTo, listWhen;
    Button transferBtn;
    EditText amount, regTransfer, accTransfer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        init();
        regTransfer.setVisibility(View.INVISIBLE);
        accTransfer.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        HashMap<String, String> activeAccounts = (HashMap<String, String>)intent.getSerializableExtra("activeAcc");

        addItemsOnSpinner(activeAccounts);

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

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == listFrom.size()){
                        regTransfer.setVisibility(View.VISIBLE);
                        accTransfer.setVisibility(View.VISIBLE);
                }else{
                    regTransfer.setVisibility(View.INVISIBLE);
                    accTransfer.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        whenSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Utility.showMessage(getApplicationContext(), "Transfer set to Now");
                        break;
                    case 1:
                        Utility.showMessage(getApplicationContext(), "Transfer set to Monthly");
                        break;
                    case 2:
                        Utility.showMessage(getApplicationContext(), "Transfer set to Yearly");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!amount.getText().toString().isEmpty()) {
                    transferData();
                }else{
                    Utility.showMessage(getApplicationContext(), "Please enter amount.");
                }
            }
        });

    }

    private void transferData() {

        final DocumentReference mDocRefFrom = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName() + "/Accounts/" + fromSpinner.getSelectedItem().toString());
        mDocRefFrom.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Double oldBalance = documentSnapshot.getDouble("balance");
                    Double amountChange = Double.parseDouble(amount.getText().toString());
                    Double newBalance = oldBalance - amountChange;
                    Map<String, Object> balanceSave = new HashMap<>();
                    balanceSave.put("balance", newBalance);
                    balanceSave.put("trans1", -amountChange);
                    mDocRefFrom.update(balanceSave);

                }
            }
        });
        if (!toSpinner.getSelectedItem().toString().equalsIgnoreCase("External")) {
            final DocumentReference mDocRefTo = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName() + "/Accounts/" + toSpinner.getSelectedItem().toString());
            mDocRefFrom.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        Double oldBalance = documentSnapshot.getDouble("balance");
                        Double amountChange = Double.parseDouble(amount.getText().toString());
                        Double newBalance = oldBalance + amountChange;
                        Map<String, Object> balanceSave = new HashMap<>();
                        balanceSave.put("balance", newBalance);
                        mDocRefTo.update(balanceSave);
                        Utility.showMessage(getApplicationContext(), "Successful Transfer");

                    }
                }
            });
        }


    }

    public void addItemsOnSpinner(HashMap activeAccounts) {
        listFrom = new ArrayList<>();
        listTo = new ArrayList<>();
        listWhen = new ArrayList();
        if(activeAccounts.get("Budget").equals("true")){
            listFrom.add("Budget");
            listTo.add("Budget");
        }
        if(activeAccounts.get("Default").equals("true")){
            listFrom.add("Default");
            listTo.add("Default");
        }
        if(activeAccounts.get("Business").equals("true")) {
            listFrom.add("Business");
            listTo.add("Business");
        }
        if(activeAccounts.get("Savings").equals("true")){
            listFrom.add("Savings");
            listTo.add("Savings");
        }
        if(activeAccounts.get("Pension").equals("true")){
            listFrom.add("Pension");
            listTo.add("Pension");
        }
        listTo.add("External");
        listWhen.add("Now");
        listWhen.add("Monthly");
        listWhen.add("Yearly");
        createSpinner(listWhen, whenSpinner);
        createSpinner(listFrom, fromSpinner);
        createSpinner(listTo, toSpinner);

    }

    private void createSpinner(ArrayList spinnerData, Spinner spinner) {
        ArrayAdapter<String> dataAdapterTransfer = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerData);
        dataAdapterTransfer.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapterTransfer);

    }

    private void init() {
        bottomNavigationView = findViewById(R.id.nav_view);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        fromSpinner = findViewById(R.id.spinnerFrom);
        toSpinner = findViewById(R.id.spinnerTo);
        whenSpinner = findViewById(R.id.spinnerWhen);
        transferBtn = findViewById(R.id.transferBtn);
        amount = findViewById(R.id.amountTransfer);
        regTransfer = findViewById(R.id.regTransfer);
        accTransfer = findViewById(R.id.accTransfer);



    }
}
