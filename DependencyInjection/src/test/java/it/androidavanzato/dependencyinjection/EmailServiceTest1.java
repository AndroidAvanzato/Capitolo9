package it.androidavanzato.dependencyinjection;

import org.junit.Before;
import org.junit.Test;

public class EmailServiceTest1 {

    private EmailService1 emailService;

    @Before
    public void init() {
        emailService = new EmailService1();
    }

    @Test
    public void testSendEmails() {
        emailService.sendEmails();
    }
}
