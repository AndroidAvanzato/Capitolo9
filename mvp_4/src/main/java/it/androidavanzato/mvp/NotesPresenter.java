package it.androidavanzato.mvp;

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
        if (model.getNotes() == null) {
            reloadData();
        }
    }

    public NotesModel getModel() {
        return model;
    }

    public void openNote(int position) {
        Note note = model.getNotes().get(position);
        view.openDetail(new EditNoteModel(note, false));
    }

    public void reloadData() {
        model.setNotes(noteService.getNotes());
    }
}
