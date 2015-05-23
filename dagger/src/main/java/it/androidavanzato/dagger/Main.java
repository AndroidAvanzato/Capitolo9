package it.androidavanzato.dagger;

public class Main {
    public static void main(String[] args) {
        EmailComponent component = DaggerEmailComponent.create();
        EmailService emailService = component.emailService();
        emailService.sendEmails();
        emailService = component.emailService();
        emailService.sendEmails();
    }
}
