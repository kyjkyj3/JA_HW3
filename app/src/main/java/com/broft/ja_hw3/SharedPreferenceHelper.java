package com.broft.ja_hw3;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kevin on 2015-06-29.
 */
public class SharedPreferenceHelper {
    private SharedPreferences mPrefs = null;
    private SharedPreferences.Editor mPrefsEditor = null;

    public SharedPreferenceHelper(Context context) {
        mPrefs = context.getSharedPreferences("HW3_SP", 0);
    }

    public SharedPreferenceHelper(Context context, String preferenceName) {
        mPrefs = context.getSharedPreferences(preferenceName, 0);
    }

    // write
    public void writePrefs(String key, int value){
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putInt(key, value).apply();
    }
    public void writePrefs(String key, long value){
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putLong(key, value).apply();
    }
    public void writePrefs(String key, float value) {
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putFloat(key, value).apply();
    }
    public void writePrefs(String key, String value){
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putString(key, value).apply();
    }
    public void writePrefs(String key, boolean value){
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putBoolean(key, value).apply();
    }

    // get
    public int getPrefsIntValue(String key){
        return getPrefsIntValue(key, 0);
    }
    public int getPrefsIntValue(String key, int defValue) { return mPrefs.getInt(key, defValue); }
    public String getPrefsStringValue(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public long getPrefsLongValue(String key){
        return mPrefs.getLong(key, 0L);
    }
    public float getPrefsFloatValue(String key){
        return mPrefs.getFloat(key, 0.0f);
    }
    public String getPrefsStringValue(String key){
        return mPrefs.getString(key, null);
    }
    public boolean getPrefsBooleanValue(String key){
        return mPrefs.getBoolean(key, false);
    }

    // remove
    public void removePrefsValue(String key){
        if(!mPrefs.getString(key, "null").equals("null")){
            mPrefsEditor.remove(key).apply();
        }
    }
}
