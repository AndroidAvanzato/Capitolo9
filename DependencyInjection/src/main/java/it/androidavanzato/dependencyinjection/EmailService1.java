package it.androidavanzato.dependencyinjection;

import java.util.Arrays;
import java.util.List;

public class EmailService1 {
    public void sendEmails() {
        List<Contact> contacts = executeContactQuery();
        for (Contact contact : contacts) {
            if (checkContact(contact)) {
                ContactPayments payments = executePaymentsQuery();
                String title = createTitle(contact, payments);
                String body = createBody(contact, payments);
                sendEmail(contact, title, body);
            }
        }
    }

    private ContactPayments executePaymentsQuery() {
        return null;
    }

    private byte[] createAttachment(Contact contact, ContactPayments payments) {
        return new byte[0];
    }

    private void sendEmail(Contact contact, String title, String body) {

    }

    private String createBody(Contact contact, ContactPayments payments) {
        return null;
    }

    private String createTitle(Contact contact, ContactPayments payments) {
        return null;
    }

    private boolean checkContact(Contact contact) {
        return false;
    }

    private List<Contact> executeContactQuery() {
        return Arrays.asList(new Contact("name"));
    }
}
