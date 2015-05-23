package it.androidavanzato.dependencyinjection;

public interface EmailSender {
    void sendEmail(Contact contact, String title, String body);
}
