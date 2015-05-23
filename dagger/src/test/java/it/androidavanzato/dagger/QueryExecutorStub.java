package it.androidavanzato.dagger;

import java.util.List;

public class QueryExecutorStub implements QueryExecutor {

    private List<Contact> contacts;

    private ContactPayments contactPayments;

    public QueryExecutorStub(List<Contact> contacts, ContactPayments contactPayments) {
        this.contacts = contacts;
        this.contactPayments = contactPayments;
    }

    @Override public List<Contact> executeContactQuery() {
        return contacts;
    }

    @Override public ContactPayments executePaymentsQuery(Contact contact) {
        return contactPayments;
    }
}
