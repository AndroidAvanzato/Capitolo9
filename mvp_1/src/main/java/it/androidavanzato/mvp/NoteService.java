package it.androidavanzato.mvp;

import java.util.Collection;

public interface NoteService {
    Collection<Note> getNotes();

    void save(Note note, boolean isNewNote);
}
