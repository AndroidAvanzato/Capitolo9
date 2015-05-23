package it.androidavanzato.mvp;

import dagger.Module;
import dagger.Provides;

@Module
public class NotesModule {

    @Provides NoteService provideNoteService(NoteServiceImpl noteService) {
        return noteService;
    }

//    @Provides NoteService provideNoteService(Application application) {
//        return new NoteService(application);
//    }
}
