package it.androidavanzato.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmtpServer {
    @Inject public SmtpServer() {
        System.out.println("creating SmtpServer");
    }

    public void sendEmail(byte[] msg) {
        System.out.println("sending email...");
    }
}
