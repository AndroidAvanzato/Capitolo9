package it.androidavanzato.jvmtest;

import java.io.IOException;

public class GcmHelper3 {

    private static final String SENDER_ID = "...";
    public static final String REGISTRATION_ID = "REGISTRATION_ID";

    private PreferencesWrapper prefs;

    private GoogleCloudMessagingWrapper gcm;

    public GcmHelper3(PreferencesWrapper prefs, GoogleCloudMessagingWrapper gcm) {
        this.prefs = prefs;
        this.gcm = gcm;
    }

    public String registerToGcm() throws IOException {
        String registrationId = prefs.getString(REGISTRATION_ID, null);
        if (registrationId == null) {
            registrationId = gcm.register(SENDER_ID);
            sendRegistrationIdToBackend(registrationId);
            prefs.putString(REGISTRATION_ID, registrationId);
        }
        return registrationId;
    }

    private void sendRegistrationIdToBackend(String registrationId) {

    }
}
