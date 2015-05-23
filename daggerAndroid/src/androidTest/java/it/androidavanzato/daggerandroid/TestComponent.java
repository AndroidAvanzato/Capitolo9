package it.androidavanzato.daggerandroid;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TestModule.class})
public interface TestComponent extends ApplicationComponent {
    void inject(MainActivityTest test);
}
