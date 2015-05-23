package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Arrays;

public class EmailServiceTest {

    private EmailService emailService;
    private EmailSender emailSender;
    private Contact contact;

    @Before
    public void init() {
        emailSender = Mockito.mock(EmailSender.class);
        contact = new Contact("name");
        emailService = new EmailService(new QueryExecutorStub(Arrays.asList(contact), new it.androidavanzato.dependencyinjection.ContactPayments()), emailSender);
    }

    @Test
    public void testSendEmails() {
        emailService.sendEmails();
        Mockito.verify(emailSender).sendEmail(Matchers.eq(contact), Matchers.anyString(), Matchers.anyString());
    }
}
