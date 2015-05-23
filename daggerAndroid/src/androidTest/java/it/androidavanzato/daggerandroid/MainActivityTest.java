package it.androidavanzato.daggerandroid;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityTest {

    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Inject ItemsService itemsService;

    @Before public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MyApplication application = (MyApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = DaggerTestComponent.builder()
                .applicationModule(new ApplicationModule(application)).build();
        component.inject(this);
        application.setComponent(component);
    }

    @Test public void testClickOnList() {
        when(itemsService.loadItems()).thenReturn(Arrays.asList("A", "B"));

        rule.launchActivity(new Intent());

        onData(is(instanceOf(String.class))).atPosition(1)
                .perform(click());

        verify(itemsService).starItem(eq("B"));
    }
}