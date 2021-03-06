package it.androidavanzato.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class NotesActivity extends ActionBarActivity {

    private NoteAdapter adapter;

    @InjectView(R.id.list) ListView listView;

    @Inject NotesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NoteApplication) getApplicationContext()).getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        adapter = new NoteAdapter(this);
        NotesModel model;
        if (savedInstanceState == null) {
            model = new NotesModel();
        } else {
            model = Parcels.unwrap(savedInstanceState.getParcelable("model"));
        }
        presenter.init(model);
        adapter.replaceData(presenter.getModel().getNotes());
        listView.setAdapter(adapter);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.subscribe(this);
    }

    @Override protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    public void updateView(NotesModel model) {
        adapter.replaceData(presenter.getModel().getNotes());
    }

    public void startLoading() {

    }

    @OnItemClick(R.id.list) void onItemClick(int position) {
        presenter.openNote(position);
    }

    public void openDetail(EditNoteModel editNoteModel) {
        EditNoteActivity.openDetail(NotesActivity.this, editNoteModel);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("model", Parcels.wrap(presenter.getModel()));
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            presenter.reloadData();
            adapter.replaceData(presenter.getModel().getNotes());
        }
    }

    public void newNote(View view) {
        openDetail(new EditNoteModel(new Note(), true));
    }
}
