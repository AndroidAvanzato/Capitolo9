package it.androidavanzato.daggerandroid;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    @Provides @Singleton ItemsService provideItemsService() {
        return Mockito.mock(ItemsService.class);
    }
}
