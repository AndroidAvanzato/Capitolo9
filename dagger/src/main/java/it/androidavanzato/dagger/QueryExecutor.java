package it.androidavanzato.dagger;

import java.util.List;

public interface QueryExecutor {
    List<Contact> executeContactQuery();

    ContactPayments executePaymentsQuery(Contact contact);
}
