package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class EmailServiceTest2 {

    private EmailService emailService;

    @Before
    public void init() {
        Contact contact = new Contact("name");
        emailService = new EmailService(new QueryExecutorStub(Arrays.asList(contact), new it.androidavanzato.dependencyinjection.ContactPayments()), new EmailSenderImpl());
    }

    @Test
    public void testSendEmails() {
        emailService.sendEmails();
    }
}
