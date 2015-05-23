package it.androidavanzato.mvp;

import org.parceler.Parcel;

@Parcel
public class EditNoteModel {
    Note note;

    boolean isNewNote;

    boolean saveExecuted;

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
}
