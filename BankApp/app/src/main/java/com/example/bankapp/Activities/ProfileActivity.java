package com.example.bankapp.Activities;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.example.bankapp.Helpers.Navigation;
import com.example.bankapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends Navigation {

    Toolbar toolbar;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    EditText name, mail, address, age, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Users/" + mAuth.getCurrentUser().getDisplayName());
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String getName = documentSnapshot.getString("name");
                    name.setText(getName);
                    String getMail = documentSnapshot.getString("email");
                    mail.setText(getMail);
                    String getAddress = documentSnapshot.getString("address");
                    address.setText(getAddress);
                    String getAge = documentSnapshot.getString("age");
                    age.setText(getAge);


                }
            }
        });
    }


    private void init(){
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.nav_view);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        name = findViewById(R.id.editName);
        mail = findViewById(R.id.editMail);
        address = findViewById(R.id.editAddress);
        age = findViewById(R.id.editAge);


    }
}
