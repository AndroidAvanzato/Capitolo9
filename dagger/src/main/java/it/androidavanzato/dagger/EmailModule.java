package it.androidavanzato.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EmailModule {
    @Provides @Singleton EmailSender provideEmailSender(SmtpServer smtpServer) {
        return new EmailSender(smtpServer);
    }

    @Provides @Singleton DatabaseManager provideDatabaseManager() {
        return new DatabaseManager("url", "user", "password");
    }

    @Provides QueryExecutor provideQueryExecutor(DatabaseManager databaseManager) {
        return new QueryExecutorImpl(databaseManager);
    }
}
