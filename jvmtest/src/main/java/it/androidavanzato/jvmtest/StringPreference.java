package it.androidavanzato.jvmtest;

public class StringPreference {
    private PreferencesWrapper preferences;
    private String key;
    private String defaultValue;

    public StringPreference(PreferencesWrapper preferences, String key, String defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String get() {
        return preferences.getString(key, defaultValue);
    }

    public void put(String value) {
        preferences.putString(key, value);
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public void remove(String key) {
        preferences.remove(key);
    }
}