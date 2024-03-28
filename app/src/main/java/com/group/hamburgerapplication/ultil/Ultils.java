package com.group.hamburgerapplication.ultil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.group.hamburgerapplication.database.UserDatabase;

public class Ultils {
    public static   void changeIntent (Context context1 , Context context2){
        Intent intent = new Intent(context1.getApplicationContext(),context2.getClass());
        context1.startActivity(intent);
    }

    public static void displayToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
    public static String convertPhone( String phone) {
        return "+84"+phone.substring(1);
    }
    void handleRedirectLogin(Context context, Activity activity){
        if(!UserDatabase.checkLogin()){
            Intent intent  = new Intent(context,activity.getClass());

        }
    }
    public static void handleBack(Activity activity) {
        activity.onBackPressed();
    }
}
