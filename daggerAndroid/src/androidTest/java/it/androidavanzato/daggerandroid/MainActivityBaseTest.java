package it.androidavanzato.daggerandroid;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class MainActivityBaseTest {

    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test public void testClickOnButton() {
        rule.launchActivity(new Intent());

        onData(is(instanceOf(String.class))).atPosition(1)
                .perform(click());
    }
}