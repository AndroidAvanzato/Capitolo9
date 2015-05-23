package it.androidavanzato.jvmtest;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringPreference stringPreference = new StringPreference(new PreferencesWrapperImpl(this), "key", "");
        GoogleCloudMessagingWrapperImpl googleCloudMessagingWrapper = new GoogleCloudMessagingWrapperImpl(this);
        GcmHelper4 gcmHelper = new GcmHelper4(stringPreference, googleCloudMessagingWrapper);
        try {
            gcmHelper.registerToGcm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
