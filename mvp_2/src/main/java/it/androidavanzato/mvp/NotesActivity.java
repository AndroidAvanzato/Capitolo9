package it.androidavanzato.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.Collection;

import javax.inject.Inject;


public class NotesActivity extends ActionBarActivity {

    private NoteAdapter adapter;

    @Inject NoteService noteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NoteApplication) getApplicationContext()).getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new NoteAdapter(this);
        Collection<Note> notes;
        if (savedInstanceState == null) {
            notes = noteService.getNotes();
        } else {
            notes = Parcels.unwrap(savedInstanceState.getParcelable("list"));
        }
        adapter.replaceData(notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = adapter.getItem(position);
                EditNoteActivity.openDetail(NotesActivity.this, note, false);
            }
        });
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("list", Parcels.wrap(adapter.getNotes()));
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            adapter.replaceData(noteService.getNotes());
        }
    }

    public void newNote(View view) {
        EditNoteActivity.openDetail(this, new Note(), true);
    }
}
