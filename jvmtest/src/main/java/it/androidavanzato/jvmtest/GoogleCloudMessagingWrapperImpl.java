package it.androidavanzato.jvmtest;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class GoogleCloudMessagingWrapperImpl implements GoogleCloudMessagingWrapper {

    private final GoogleCloudMessaging gcm;

    public GoogleCloudMessagingWrapperImpl(Context context) {
        gcm = GoogleCloudMessaging.getInstance(context);
    }

    @Override public String register(String senderId) throws IOException {
        return gcm.register(senderId);
    }
}
