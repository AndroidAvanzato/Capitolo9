package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest5 {

    private EmailService emailService;
    @Mock private EmailSender emailSender;
    @Mock private QueryExecutor queryExecutor;

    @Before
    public void init() {
        emailService = new EmailService(queryExecutor, emailSender);
    }

    @Test
    public void testSendEmails() {
        Contact contact = new Contact("name");
        Mockito.when(queryExecutor.executeContactQuery()).thenReturn(Arrays.asList(contact));
        Mockito.when(queryExecutor.executePaymentsQuery(any(Contact.class))).thenReturn(new it.androidavanzato.dependencyinjection.ContactPayments());

        emailService.sendEmails();

        Mockito.verify(emailSender).sendEmail(eq(contact), anyString(), anyString());
    }
}
