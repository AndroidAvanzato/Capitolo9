package it.androidavanzato.mvp;

import android.app.Application;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;

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

    @Override public Observable<List<Note>> getNotes() {
        return Observable.<List<Note>>just(new ArrayList<>(allNotes.values())).doOnNext(new Action1<List<Note>>() {
            @Override public void call(List<Note> notes) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
            }
        });
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
