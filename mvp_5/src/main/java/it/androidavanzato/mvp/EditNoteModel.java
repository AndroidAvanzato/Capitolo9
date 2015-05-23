package it.androidavanzato.mvp;

import org.parceler.Parcel;

@Parcel
public class EditNoteModel {
    private Note note;

    private boolean isNewNote;

    private boolean saveExecuted;

    private boolean titleWithError;

    private boolean descriptionWithError;

    public EditNoteModel() {
    }

    public EditNoteModel(Note note) {
        this.note = note;
    }

    public EditNoteModel(Note note, boolean isNewNote) {
        this.note = note;
        this.isNewNote = isNewNote;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public boolean isNewNote() {
        return isNewNote;
    }

    public void setNewNote(boolean isNewNote) {
        this.isNewNote = isNewNote;
    }

    public boolean isSaveExecuted() {
        return saveExecuted;
    }

    public void setSaveExecuted(boolean saveExecuted) {
        this.saveExecuted = saveExecuted;
    }

    public boolean isTitleWithError() {
        return titleWithError;
    }

    public void setTitleWithError(boolean titleWithError) {
        this.titleWithError = titleWithError;
    }

    public boolean isDescriptionWithError() {
        return descriptionWithError;
    }

    public void setDescriptionWithError(boolean descriptionWithError) {
        this.descriptionWithError = descriptionWithError;
    }
}
