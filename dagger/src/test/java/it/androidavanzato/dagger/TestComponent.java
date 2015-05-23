package it.androidavanzato.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent extends EmailComponent {
    void inject(EmailServiceTest test);
}
