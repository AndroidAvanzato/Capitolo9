package it.androidavanzato.jvmtest;

import android.content.SharedPreferences;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class GcmHelper2 {

    private static final String SENDER_ID = "...";
    public static final String REGISTRATION_ID = "REGISTRATION_ID";

    private SharedPreferences prefs;

    private GoogleCloudMessaging gcm;

    public GcmHelper2(SharedPreferences prefs, GoogleCloudMessaging gcm) {
        this.prefs = prefs;
        this.gcm = gcm;
    }

    public String registerToGcm() throws IOException {
        String registrationId = prefs.getString(REGISTRATION_ID, null);
        if (registrationId == null) {
            registrationId = gcm.register(SENDER_ID);
            sendRegistrationIdToBackend(registrationId);
            prefs.edit().putString(REGISTRATION_ID, registrationId).apply();
        }
        return registrationId;
    }

    private void sendRegistrationIdToBackend(String registrationId) {

    }
}
