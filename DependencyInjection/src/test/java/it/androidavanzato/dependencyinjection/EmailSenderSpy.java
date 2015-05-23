package it.androidavanzato.dependencyinjection;

public class EmailSenderSpy implements EmailSender {

    private boolean methodCalled;

    @Override public void sendEmail(Contact contact, String title, String body) {
        methodCalled = true;
    }

    public boolean isMethodCalled() {
        return methodCalled;
    }
}
