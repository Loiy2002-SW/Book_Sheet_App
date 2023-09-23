
package com.loiy.booksheet.SharedPref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.loiy.booksheet.models.User;
import com.loiy.booksheet.security.LoginScreen;

public class SharedPrefManager {

    //declaring and initializing the keys(values to be saved).
    private static final String SHARED_PREF_NAME = "EStoreSharedPref";
    private static final String KEY_ID = "KeyId";
    private static final String KEY_USERNAME = "KeyUsername";
    private static final String KEY_EMAIL = "KeyEmail";
    private static final String KEY_PASSWORD = "KeyPassword";
    private static final String KEY_PHONE = "KeyPhone";


    //declaring an instance of SharedPrefManager to used it later.
    private static SharedPrefManager mInstance;

    //declaring the context that we will deal with.
    private static Context mCtx;

    //one-parameter constructor to get the context
    private SharedPrefManager(Context context){
        mCtx = context;
    }


    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized SharedPrefManager getInstance(Context c){

        if(mInstance == null)
            mInstance = new SharedPrefManager(c);

        return mInstance;

    }

    //userLogin method to save the user info after he logged in.
    public void userLogin(User user){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_USERNAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_PASSWORD,user.getPassword());
        editor.putString(KEY_PHONE,user.getPhone());
        editor.apply();

    }

    //userUpdate method to save the new user info (updated info).
    public void userUpdate(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE,user.getPhone());
        editor.apply();
    }


    //getUsers method to return the saved user info.
    public User getUsers(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getInt(KEY_ID,0),
                sharedPreferences.getString(KEY_USERNAME,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_PASSWORD,null),
                sharedPreferences.getString(KEY_PHONE,null)
        );

    }

    //isLoggedIn method to check whether the user logged in or not.
    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_USERNAME,null) != null;
    }

    //Logout method to delete the saved user info and open login screen.
    public void Logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        Intent i = new Intent(mCtx, LoginScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(i);


    }
}






