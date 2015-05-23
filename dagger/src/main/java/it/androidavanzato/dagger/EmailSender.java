package it.androidavanzato.dagger;

public class EmailSender {

    private SmtpServer smtpServer;

    public EmailSender(SmtpServer smtpServer) {
        this.smtpServer = smtpServer;
        System.out.println("creating email sender");
    }

    public void sendEmail(Contact contact, String title, String body) {
        System.out.println("before...");
        smtpServer.sendEmail(new byte[0]);
        System.out.println("after...");
    }
}
