package it.androidavanzato.mvp;

import android.app.Application;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteServiceImpl implements NoteService {

    private Map<Long, Note> allNotes = createInitialData();

    private Map<Long, Note> createInitialData() {
        Map<Long, Note> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put((long) i, create(i));
        }
        return map;
    }

    @Inject public NoteServiceImpl(Application application) {
    }

    @Override public java.util.List<Note> getNotes() {
        return new ArrayList<>(allNotes.values());
    }

    private Note create(int id) {
        return new Note(id, "Note " + id, "Note " + id);
    }

    @Override public void save(Note note, boolean isNewNote) {
        if (isNewNote) {
            note.setId(allNotes.size() + 1);
        }
        allNotes.put(note.getId(), note);
    }
}
