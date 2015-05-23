package it.androidavanzato.dagger;

import java.util.List;

import javax.inject.Inject;

public class EmailService {

    private QueryExecutor queryExecutor;

    private EmailSender emailSender;

    @Inject public EmailService(QueryExecutor queryExecutor, EmailSender emailSender) {
        this.queryExecutor = queryExecutor;
        this.emailSender = emailSender;
    }

    public void sendEmails() {
        List<Contact> contacts = queryExecutor.executeContactQuery();
        for (Contact contact : contacts) {
            if (checkContact(contact)) {
                ContactPayments payments = queryExecutor.executePaymentsQuery(contact);
                String title = createTitle(contact, payments);
                String body = createBody(contact, payments);
                emailSender.sendEmail(contact, title, body);
            }
        }
    }

    private byte[] createAttachment(Contact contact, ContactPayments payments) {
        return new byte[0];
    }

    private String createBody(Contact contact, ContactPayments payments) {
        return "body";
    }

    private String createTitle(Contact contact, ContactPayments payments) {
        return "title";
    }

    private boolean checkContact(Contact contact) {
        return true;
    }

}
