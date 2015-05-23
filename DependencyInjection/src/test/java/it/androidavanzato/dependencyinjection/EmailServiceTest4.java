package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

public class EmailServiceTest4 {

    private EmailService emailService;
    private EmailSender emailSender;
    private QueryExecutor queryExecutor;

    @Before
    public void init() {
        emailSender = Mockito.mock(EmailSender.class);
        queryExecutor = Mockito.mock(QueryExecutor.class);
        emailService = new EmailService(queryExecutor, emailSender);
    }

    @Test
    public void testSendEmails() {
        Contact contact = new Contact("name");
        Mockito.when(queryExecutor.executeContactQuery()).thenReturn(Arrays.asList(contact));
        Mockito.when(queryExecutor.executePaymentsQuery(contact)).thenReturn(new it.androidavanzato.dependencyinjection.ContactPayments());

        emailService.sendEmails();

        Mockito.verify(emailSender).sendEmail(eq(contact), anyString(), anyString());
    }
}
