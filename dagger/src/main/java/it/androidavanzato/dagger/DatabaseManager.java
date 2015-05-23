package it.androidavanzato.dagger;

import javax.inject.Inject;

public class DatabaseManager {
    @Inject public DatabaseManager(String url, String user, String password) {
    }
}
