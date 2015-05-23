package it.androidavanzato.dependencyinjection;

import java.util.List;

public class EmailService2 {

    private QueryExecutor queryExecutor = new it.androidavanzato.dependencyinjection.QueryExecutorImpl();

    private it.androidavanzato.dependencyinjection.EmailSender emailSender = new it.androidavanzato.dependencyinjection.EmailSenderImpl();

    public void sendEmails() {
        List<it.androidavanzato.dependencyinjection.Contact> contacts = queryExecutor.executeContactQuery();
        for (it.androidavanzato.dependencyinjection.Contact contact : contacts) {
            if (checkContact(contact)) {
                it.androidavanzato.dependencyinjection.ContactPayments payments = queryExecutor.executePaymentsQuery(contact);
                String title = createTitle(contact, payments);
                String body = createBody(contact, payments);
                emailSender.sendEmail(contact, title, body);
            }
        }
    }

    private byte[] createAttachment(it.androidavanzato.dependencyinjection.Contact contact, it.androidavanzato.dependencyinjection.ContactPayments payments) {
        return new byte[0];
    }

    private String createBody(it.androidavanzato.dependencyinjection.Contact contact, it.androidavanzato.dependencyinjection.ContactPayments payments) {
        return null;
    }

    private String createTitle(it.androidavanzato.dependencyinjection.Contact contact, it.androidavanzato.dependencyinjection.ContactPayments payments) {
        return null;
    }

    private boolean checkContact(it.androidavanzato.dependencyinjection.Contact contact) {
        return false;
    }

}
