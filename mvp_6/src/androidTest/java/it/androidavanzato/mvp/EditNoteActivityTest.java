package it.androidavanzato.mvp;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class EditNoteActivityTest {

    @Rule public ActivityTestRule<EditNoteActivity> rule = new ActivityTestRule<>(EditNoteActivity.class, false, false);

    @Inject NoteService noteService;

    @Before public void setUp() {
        NoteApplication application = (NoteApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        TestComponent component = DaggerTestComponent.builder().applicationModule(new ApplicationModule(application)).build();
        component.inject(this);
        application.setComponent(component);
    }

    @Test public void testSaveNewNote() {
        Intent intent = EditNoteActivity.populateIntent(new Intent(), new EditNoteModel(new Note(1, "a", "b"), false));
        rule.launchActivity(intent);

        onView(withId(R.id.title))
                .perform(clearText(), typeText("new title"));
        onView(withId(R.id.description))
                .perform(clearText(), typeText("new description"));

        onView(withId(R.id.save)).perform(click());

        ArgumentCaptor<Note> captor = ArgumentCaptor.forClass(Note.class);

        verify(noteService).save(captor.capture(), eq(false));
        assertEquals("new title", captor.getValue().getTitle());
        assertEquals("new description", captor.getValue().getDescription());
    }
}
