package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class EmailServiceTest3 {

    private it.androidavanzato.dependencyinjection.EmailService emailService;
    private EmailSenderSpy emailSender;

    @Before
    public void init() {
        emailSender = new EmailSenderSpy();
        Contact contact = new Contact("name");
        emailService = new it.androidavanzato.dependencyinjection.EmailService(new QueryExecutorStub(Arrays.asList(contact), new it.androidavanzato.dependencyinjection.ContactPayments()), emailSender);
    }

    @Test
    public void testSendEmails() {
        emailService.sendEmails();
        assertTrue(emailSender.isMethodCalled());
    }
}
