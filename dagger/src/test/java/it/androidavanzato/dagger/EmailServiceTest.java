package it.androidavanzato.dagger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Arrays;

import javax.inject.Inject;

public class EmailServiceTest {

    @Inject EmailService emailService;
    @Inject EmailSender emailSender;
    private Contact contact;

    @Before
    public void init() {
        contact = new Contact("name");
        new TestModule(Arrays.asList(contact), new ContactPayments());
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule(Arrays.asList(contact), new ContactPayments())).build();
        component.inject(this);
    }

    @Test
    public void testSendEmails() {
        emailService.sendEmails();
        Mockito.verify(emailSender).sendEmail(Matchers.eq(contact), Matchers.anyString(), Matchers.anyString());
    }

}