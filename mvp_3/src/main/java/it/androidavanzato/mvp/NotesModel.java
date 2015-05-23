package it.androidavanzato.mvp;

import java.util.List;

public class NotesModel {

    List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
