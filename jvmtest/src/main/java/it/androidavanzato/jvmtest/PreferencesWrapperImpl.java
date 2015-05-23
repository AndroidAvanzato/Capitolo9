package it.androidavanzato.jvmtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesWrapperImpl implements PreferencesWrapper {

    private SharedPreferences preferences;

    public PreferencesWrapperImpl(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    @Override public void putString(String key, String value) {

    }

    @Override public boolean contains(String key) {
        return false;
    }

    @Override public void remove(String key) {

    }

    @Override public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override public void putBoolean(String key, boolean value) {

    }

    @Override public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override public void putInt(String key, int value) {

    }

    @Override public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override public void putLong(String key, long value) {

    }

    @Override public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override public void putFloat(String key, float value) {

    }
}
