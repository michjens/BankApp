package com.example.bankapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bankapp.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView acc1, acc2, acc3, acc4, acc5, acc1amount, acc2amount, acc3amount, acc4amount, acc5amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


    }

    private void init() {
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
