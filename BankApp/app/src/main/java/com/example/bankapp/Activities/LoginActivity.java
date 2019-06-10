package com.example.bankapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.bankapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.bankapp.Helpers.Utility.*;


public class LoginActivity extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button btnLogin, forgotPass, btnReg;
    private ProgressBar loginProg;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginProg.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProg.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if(mail.isEmpty() || password.isEmpty()){
                    showMessage(getApplicationContext(),"Please verify all fields");

                }else{
                    login(mail, password);

                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI(LoginActivity.this, RegisterActivity.class);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMail.getText().toString().isEmpty()) {
                    showMessage(getApplicationContext(), "Enter Email");
                } else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(userMail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                showMessage(getApplicationContext(), "Email sent");
                            }
                        }
                    });
                }
            }
        });

    }

    private void login(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginProg.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI(LoginActivity.this, HomeActivity.class);
                }else{
                    showMessage(getApplicationContext(), "Wrong Email and/or Password");
                    loginProg.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void init() {
        userMail = findViewById(R.id.logMail);
        userPassword = findViewById(R.id.logPass);
        btnLogin = findViewById(R.id.logBtn);
        loginProg = findViewById(R.id.loginProg);
        mAuth = FirebaseAuth.getInstance();
        forgotPass = findViewById(R.id.loginForgot);
        btnReg = findViewById(R.id.btnReg);




    }
}
