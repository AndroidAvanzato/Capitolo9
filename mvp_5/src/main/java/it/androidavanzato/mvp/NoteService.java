package it.androidavanzato.mvp;

import java.util.List;

import rx.Observable;

public interface NoteService {
    Observable<List<Note>> getNotes();

    void save(Note note, boolean isNewNote);
}
