package it.androidavanzato.jvmtest;

import java.io.IOException;

public interface GoogleCloudMessagingWrapper {
    String register(String senderId) throws IOException;
}
