package com.learning.deznorth.laso;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Deznorth on 7/22/2017.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_FIRST_NAME = "userfirstname";
    private static final String KEY_USER_LAST_NAME = "userlastname";
    private static final String KEY_ROLE = "userRole";
    private static final String KEY_ROLE_TITLE = "roleTitle";
    private static final String KEY_ROLE_DESCRIPTION = "roleDesc";
    private static final String KEY_ROLE_POWER = "rolePower";
    private static final String KEY_MEMBER_MANAGEMENT = "";
    private static final String KEY_EBOARD_MANAGEMENT = "";
    private static final String KEY_SUPER_EBOARD_MANAGEMENT = "";
    private static final String KEY_EVENT_MANAGEMENT = "";


    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String username, String email, String fName, String lName, int role){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_FIRST_NAME, fName);
        editor.putString(KEY_USER_LAST_NAME, lName);
        editor.putInt(KEY_ROLE, role);

        editor.apply();

        return true;
    }

    public boolean updateRole(String title, int power, String description){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ROLE_TITLE, title);
        editor.putString(KEY_ROLE_DESCRIPTION, description);
        editor.putInt(KEY_ROLE_POWER, power);

        editor.apply();

        return true;
    }

    public boolean updateRoleId(int roleId){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ROLE, roleId);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUserName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_FIRST_NAME, null)
                + " " + sharedPreferences.getString(KEY_USER_LAST_NAME, null);
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);

    }

    public int getUserRole(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ROLE, 0);

    }
    public String getRoleTitle(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE_TITLE, null);

    }

    public String getRoleDescription(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE_DESCRIPTION, null);

    }

    public int getRolePower(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ROLE_POWER, 0);

    }

}
