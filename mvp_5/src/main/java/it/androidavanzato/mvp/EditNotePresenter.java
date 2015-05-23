package it.androidavanzato.mvp;

import javax.inject.Inject;

public class EditNotePresenter {
    private EditNoteModel model;

    private NoteService noteService;
    private EditNoteActivity view;

    @Inject public EditNotePresenter(NoteService noteService) {
        this.noteService = noteService;
    }

    public void init(EditNoteModel model, EditNoteActivity view) {
        this.model = model;
        this.view = view;
    }

    public void validateFields(String title, String description) {
        if (model.isSaveExecuted()) {
            executeValidation(title, description);
            view.populateView(model);
        }
    }

private boolean executeValidation(String title, String description) {
    model.getNote().setTitle(title);
    model.getNote().setDescription(description);
    model.setTitleWithError(title.isEmpty());
    model.setDescriptionWithError(description.isEmpty());
    return !model.isTitleWithError() && !model.isDescriptionWithError();
}

    public void save(String title, String description) {
        model.setSaveExecuted(true);
        if (executeValidation(title, description)) {
            noteService.save(model.getNote(), model.isNewNote());
            view.setResultOkAndFinish();
        } else {
            view.populateView(model);
        }
    }

    public EditNoteModel getModel() {
        return model;
    }
}
