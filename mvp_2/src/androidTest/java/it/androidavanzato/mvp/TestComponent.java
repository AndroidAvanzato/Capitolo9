package it.androidavanzato.mvp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TestModule.class})
public interface TestComponent extends ApplicationComponent {
    void inject(EditNoteActivityTest test);
}
