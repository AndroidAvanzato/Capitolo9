package it.androidavanzato.mvp;

import java.util.List;

import javax.inject.Inject;

public class NotesPresenter {

    private NotesModel model;

    private NotesActivity view;

    private NoteService noteService;

    @Inject public NotesPresenter(NoteService noteService) {
        this.noteService = noteService;
    }

    public void init(NotesModel model, NotesActivity view) {
        this.model = model;
        this.view = view;
    }

    public List<Note> loadNotes() {
        return noteService.getNotes();
    }

    public NotesModel getModel() {
        return model;
    }

    public void openNote(int position) {
        Note note = model.getNotes().get(position);
        view.openDetail(note, false);
    }
}
