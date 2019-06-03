package com.example.bankapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bankapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail, userPassword, userPassword2, userName;
    private ProgressBar loadingProg;
    private Button registerBtn, loginBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        loadingProg.setVisibility(View.INVISIBLE);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerBtn.setVisibility(View.INVISIBLE);
                loadingProg.setVisibility(View.VISIBLE);
                final String name = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty()|| password2.isEmpty() || !password.equals(password2)){
                    showMessage("Please verify all fields");
                    registerBtn.setVisibility(View.VISIBLE);
                    loadingProg.setVisibility(View.INVISIBLE);
                }else{
                    createUserAcc(email, name, password);
                }

            }
        });


    }

    private void createUserAcc(String email, final String name, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    showMessage("User registration complete");
                    updateInfo(name, mAuth.getCurrentUser());


                }else{
                    showMessage("User registration failed " + "\n" + task.getException().getMessage());
                    registerBtn.setVisibility(View.VISIBLE);
                    loadingProg.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void updateInfo(String name, FirebaseUser currentUser) {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
        currentUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    showMessage("User registration complete");
                    updateUI();


                }
            }
        });

    }

    private void updateUI() {
        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(home);
    }

    private void showMessage(String msgIn) {
        Toast.makeText(getApplicationContext(), msgIn, Toast.LENGTH_LONG).show();
    }

    private void init() {
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPass);
        userPassword2 = findViewById(R.id.regPass2);
        userName = findViewById(R.id.regName);
        loadingProg = findViewById(R.id.progressBar);
        registerBtn = findViewById(R.id.regBtn);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.logBtn);
    }
}
