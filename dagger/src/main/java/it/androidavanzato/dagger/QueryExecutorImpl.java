package it.androidavanzato.dagger;

import java.util.Arrays;
import java.util.List;

public class QueryExecutorImpl implements QueryExecutor {

    private DatabaseManager databaseManager;

    public QueryExecutorImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override public List<Contact> executeContactQuery() {
        return Arrays.asList(new Contact("name"));
    }

    @Override public ContactPayments executePaymentsQuery(Contact contact) {
        return new ContactPayments();
    }
}
