package it.androidavanzato.mvp;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    @Provides @Singleton NoteService provideNoteService() {
        return Mockito.mock(NoteService.class);
    }
}
