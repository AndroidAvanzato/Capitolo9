package it.androidavanzato.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = EmailModule.class)
public interface EmailComponent {
    EmailService emailService();
}
