package it.androidavanzato.mvp;

import org.parceler.Parcel;

@Parcel
public class EditNoteModel {
    private Note note;

    private boolean isNewNote;

    private boolean saveExecuted;

    private int titleError;

    private int descriptionError;

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

    public int getTitleError() {
        return titleError;
    }

    public void setTitleError(int titleError) {
        this.titleError = titleError;
    }

    public int getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(int descriptionError) {
        this.descriptionError = descriptionError;
    }
}
