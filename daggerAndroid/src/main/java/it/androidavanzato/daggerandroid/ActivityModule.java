package it.androidavanzato.daggerandroid;

import android.app.Activity;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides Activity provideActivity() {
        return activity;
    }

    @Provides @ActivityScope LayoutInflater provideLayoutInflater() {
        System.out.println("Provide LayoutInflater");
        return activity.getLayoutInflater();
    }
}
