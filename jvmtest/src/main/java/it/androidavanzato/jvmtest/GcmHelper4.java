package it.androidavanzato.jvmtest;

import java.io.IOException;

public class GcmHelper4 {

    private static final String SENDER_ID = "...";

    private StringPreference prefs;

    private GoogleCloudMessagingWrapper gcm;

    public GcmHelper4(StringPreference prefs, GoogleCloudMessagingWrapper gcm) {
        this.prefs = prefs;
        this.gcm = gcm;
    }

    public String registerToGcm() throws IOException {
        String registrationId = prefs.get();
        if (registrationId == null) {
            registrationId = gcm.register(SENDER_ID);
            sendRegistrationIdToBackend(registrationId);
            prefs.put(registrationId);
        }
        return registrationId;
    }

    private void sendRegistrationIdToBackend(String registrationId) {

    }
}
