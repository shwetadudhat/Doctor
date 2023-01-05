package com.sparrow.doctor.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.sparrow.doctor.Activity.LoginActivity;
import com.sparrow.doctor.Activity.MainActivity;

import java.util.HashMap;


public class Session_management {

    SharedPreferences prefs;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    public Session_management(Context context) {

        this.context = context;
        prefs = context.getSharedPreferences("Daily", PRIVATE_MODE);
        editor = prefs.edit();


    }

    public void createLoginSession(String id, String name,String pass) {
        editor.putBoolean(Constants.IS_LOGIN, true);
        editor.putString(Constants.UserId, id);
        editor.putString(Constants.UserName, name);
        editor.putString(Constants.Password, pass);

       // editor.putString(BaseURL.KEY_PINCODE,pincode);

        editor.commit();
        editor.apply();
    }


//
    public boolean  checkLogin() {

        if (!this.isLoggedIn()) {
            Intent loginsucces = new Intent(context, MainActivity.class);
            // Closing all the Activities
            loginsucces.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            loginsucces.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(loginsucces);

            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(Constants.UserId, prefs.getString(Constants.UserId, null));
        // user email id
        user.put(Constants.UserName, prefs.getString(Constants.UserName, null));
        // user name

//        user.put(KEY_TYPE, prefs.getString(KEY_TYPE,null));

        // return user
        return user;
    }


    public void logoutSession() {
        editor.clear();
        editor.commit();

        Intent logout = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(logout);
    }


//     Get Login State
    public boolean isLoggedIn() {
        return prefs.getBoolean(Constants.IS_LOGIN, false);
    }

}
