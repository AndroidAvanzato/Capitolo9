package it.androidavanzato.daggerandroid;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
