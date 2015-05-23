package it.androidavanzato.mvp;

import java.util.List;

public interface NoteService {
    List<Note> getNotes();

    void save(Note note, boolean isNewNote);
}
