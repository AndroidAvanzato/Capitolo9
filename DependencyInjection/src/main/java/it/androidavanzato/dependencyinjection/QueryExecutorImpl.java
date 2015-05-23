package it.androidavanzato.dependencyinjection;

import java.util.List;

public class QueryExecutorImpl implements QueryExecutor {
    @Override public List<Contact> executeContactQuery() {
        return null;
    }

    @Override public ContactPayments executePaymentsQuery(Contact contact) {
        return null;
    }
}
