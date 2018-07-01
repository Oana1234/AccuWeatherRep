package com.example.ileana.accuweather.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ILEANA on 3/13/2018.
 */

public class LocalStorage {

    private static final String PREF_FILE = "";

    private SharedPreferences sharedPreferences;

    private static LocalStorage instance;

    public static LocalStorage getInstance(Context context) {
        if (instance == null) {
            instance = new LocalStorage(context);
        }
        return instance;
    }

    private LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String defaultValue) {
        sharedPreferences.edit().putString(key, defaultValue).apply();
    }

}
