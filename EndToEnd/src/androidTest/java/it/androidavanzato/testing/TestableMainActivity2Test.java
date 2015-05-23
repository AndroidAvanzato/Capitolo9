package it.androidavanzato.testing;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class TestableMainActivity2Test {

    @Rule public final ActivityTestRule<TestableMainActivity2> rule = new ActivityTestRule<>(TestableMainActivity2.class, false, false);

    @Test public void testCalculateMax() {
        TestableMainActivity2.items = Arrays.asList("X", "Y", "Z");

        rule.launchActivity(new Intent());

        onView(withId(R.id.result)).check(matches(withText("X")));
    }
}
