package it.androidavanzato.mvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditNoteMvpTest {

    private EditNotePresenter presenter;

    @Mock NoteService noteService;

    @Mock EditNoteActivity activity;

    @Captor ArgumentCaptor<Note> captor;

    private EditNoteView view;

    private TestInjector testInjector;

    @Before
    public void init() {
        presenter = new EditNotePresenter(noteService);
        view = new EditNoteView(activity, presenter);
        testInjector = new TestInjector();
        testInjector.inject(view);

        when(activity.getErrorMessage(anyInt())).thenReturn("Error");
    }

    @Test
    public void testEmptyFields() {
        EditNoteModel model = new EditNoteModel(new Note());
        presenter.init(model, view);

        testInjector.clickOnView(R.id.save);

        assertNotNull(testInjector.getTextView(R.id.title).getError());
        assertNotNull(testInjector.getTextView(R.id.description).getError());

        verify(activity, never()).setResultOkAndFinish();
    }

    @Test
    public void testClickOnSave() {
        EditNoteModel model = new EditNoteModel(new Note(), true);
        presenter.init(model, view);

        testInjector.getTextView(R.id.title).setText("new title");
        testInjector.getTextView(R.id.description).setText("new description");
        testInjector.clickOnView(R.id.save);

        assertNull(testInjector.getTextView(R.id.title).getError());
        assertNull(testInjector.getTextView(R.id.description).getError());

        verify(noteService).save(captor.capture(), eq(true));
        assertEquals("new title", captor.getValue().getTitle());
        assertEquals("new description", captor.getValue().getDescription());

        verify(activity).setResultOkAndFinish();
    }
}
