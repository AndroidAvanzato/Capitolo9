package it.androidavanzato.mvp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotesPresenterTest {

    @Mock NoteService noteService;

    @Mock NotesActivity view;

    @Captor ArgumentCaptor<EditNoteModel> captor;

    @Test
    public void testClickInList() {
        NotesPresenter presenter = new NotesPresenter(noteService);
        when(noteService.getNotes()).thenReturn(
                Arrays.asList(new Note(1, "A", "B"), new Note(2, "C", "D"))
        );
        NotesModel model = new NotesModel();
        presenter.init(model, view);
        presenter.openNote(1);

        verify(view).openDetail(captor.capture());
        EditNoteModel editNoteModel = captor.getValue();
        assertEquals(2, editNoteModel.getNote().getId());
    }
}
