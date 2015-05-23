package it.androidavanzato.mvp;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {
    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides Application provideApplication() {
        return application;
    }

    @Provides NotesPresenter provideNotesPresenter(NoteService noteService) {
        return new NotesPresenter(noteService, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
