package it.androidavanzato.mvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EditNotePresenterTest {

    private EditNotePresenter presenter;

    @Mock NoteService noteService;

    @Mock EditNoteActivity view;

    @Before
    public void init() {
        presenter = new EditNotePresenter(noteService);
    }

    @Test
    public void testEmptyFields() {
        EditNoteModel model = new EditNoteModel(new Note());
        presenter.init(model, view);
        presenter.save("", "");
        assertEquals(R.string.mandatory_field, model.getTitleError());
        assertEquals(R.string.mandatory_field, model.getDescriptionError());

        verify(view, never()).setResultOkAndFinish();
    }

    @Test
    public void testFields() {
        EditNoteModel model = new EditNoteModel(new Note());
        presenter.init(model, view);
        presenter.save("a", "b");
        assertEquals(-1, model.getTitleError());
        assertEquals(-1, model.getDescriptionError());

        verify(view).setResultOkAndFinish();
    }
}
