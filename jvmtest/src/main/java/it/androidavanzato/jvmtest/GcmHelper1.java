package it.androidavanzato.jvmtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class GcmHelper1 {

    private static final String SENDER_ID = "...";
    public static final String REGISTRATION_ID = "REGISTRATION_ID";

    public String registerToGcm(Context context) throws IOException {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String registrationId = prefs.getString(REGISTRATION_ID, null);
        if (registrationId == null) {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
            registrationId = gcm.register(SENDER_ID);
            sendRegistrationIdToBackend(registrationId);
            prefs.edit().putString(REGISTRATION_ID, registrationId).apply();
        }
        return registrationId;
    }

    private void sendRegistrationIdToBackend(String registrationId) {

    }
}
