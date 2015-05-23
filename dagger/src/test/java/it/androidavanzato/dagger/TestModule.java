package it.androidavanzato.dagger;

import org.mockito.Mockito;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    private final List<Contact> contacts;
    private final ContactPayments contactPayments;

    public TestModule(List<Contact> contacts, ContactPayments contactPayments) {
        this.contacts = contacts;
        this.contactPayments = contactPayments;
    }

    @Provides QueryExecutor provideQueryExecutor() {
        return new QueryExecutorStub(contacts, contactPayments);
    }

    @Provides @Singleton EmailSender provideEmailSender() {
        return Mockito.mock(EmailSender.class);
    }
}
