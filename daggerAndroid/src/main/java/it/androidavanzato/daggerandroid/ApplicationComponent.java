package it.androidavanzato.daggerandroid;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ItemsService getItemsService();
}
