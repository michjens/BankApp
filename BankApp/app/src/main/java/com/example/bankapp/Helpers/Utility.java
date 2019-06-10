package com.example.bankapp.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Utility {


    public static void showMessage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public static void updateUI(Activity oldActivity, Class newActivity){
        Intent update = new Intent(oldActivity, newActivity);
        oldActivity.startActivity(update);
    }

}
