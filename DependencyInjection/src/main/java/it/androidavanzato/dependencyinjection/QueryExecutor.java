package it.androidavanzato.dependencyinjection;

import java.util.List;

public interface QueryExecutor {
    List<it.androidavanzato.dependencyinjection.Contact> executeContactQuery();

    ContactPayments executePaymentsQuery(it.androidavanzato.dependencyinjection.Contact contact);
}
