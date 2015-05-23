package it.androidavanzato.mvp;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class NotesModel {

    List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
