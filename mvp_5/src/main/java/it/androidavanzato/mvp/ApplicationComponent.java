package it.androidavanzato.mvp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NotesModule.class})
public interface ApplicationComponent {
    void inject(NotesActivity activity);

    void inject(EditNoteActivity activity);
}
