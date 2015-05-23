package it.androidavanzato.mvp;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class NotesModel {

    List<Note> notes = new ArrayList<>();

    boolean error;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
