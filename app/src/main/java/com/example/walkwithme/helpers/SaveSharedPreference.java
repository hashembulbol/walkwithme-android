package com.example.walkwithme.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.util.Log;

public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";
    static final String PREF_PWD= "password";
    static final String PREF_COUNT= "count";
    static final String PREF_OLDCOUNT= "oldcount";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void clear(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    public static void setPassword(Context context, String pwd){

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_PWD, pwd);
        editor.commit();

    }

    public static String getPassword(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PWD, "");
    }

    public static void setCount(Context context, int count){
        Log.d("setCount", String.valueOf(count));

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(PREF_COUNT, count);
        editor.commit();

    }

    public static int getCount(Context ctx)
    {
        int count = getSharedPreferences(ctx).getInt(PREF_COUNT, 0);
        Log.d("getCount", String.valueOf(count));

        return count;

    }

    public static void incrementCount(Context context, int count){


        Log.d("incrementCount: Input ", String.valueOf(count));

        int difference = getOldCount(context) - getCount(context);
        Log.d("incrementCount: Difference ", String.valueOf(count));

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        editor.putInt(PREF_COUNT, count + difference);

        editor.commit();
        Log.d("AFter incrementCount: Difference ", String.valueOf(count));

        setOldCount(context, getCount(context));
    }

    public static void setOldCount(Context context, int oldcount){
        Log.d("setOldCount", String.valueOf(oldcount));
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(PREF_OLDCOUNT, oldcount);
        editor.commit();

    }

    public static int getOldCount(Context ctx)
    {
        int oldCount = getSharedPreferences(ctx).getInt(PREF_OLDCOUNT, 0);
        Log.d("getOldCount", String.valueOf(oldCount));
        return oldCount;
    }





}