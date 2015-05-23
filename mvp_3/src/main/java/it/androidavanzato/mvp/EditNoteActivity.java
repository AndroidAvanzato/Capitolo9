package it.androidavanzato.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnFocusChange;

public class EditNoteActivity extends ActionBarActivity {

    private static final String NOTE = "note";
    private static final String IS_NEW_NOTE = "isNewNote";
    private static final String MODEL = "model";

    @InjectView(R.id.title) EditText titleView;

    @InjectView(R.id.description) EditText descriptionView;

    @Inject EditNotePresenter presenter;

    public static void openDetail(Activity activity, Note note, boolean isNewNote) {
        Intent intent = new Intent(activity, EditNoteActivity.class);
        populateIntent(intent, note, isNewNote);
        activity.startActivityForResult(intent, 1);
    }

    public static Intent populateIntent(Intent intent, Note note, boolean isNewNote) {
        intent.putExtra(NOTE, Parcels.wrap(note));
        intent.putExtra(IS_NEW_NOTE, isNewNote);
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NoteApplication) getApplicationContext()).getComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        EditNoteModel model;
        if (savedInstanceState == null) {
            model = new EditNoteModel();
            model.setNote(Parcels.<Note>unwrap(getIntent().getParcelableExtra(NOTE)));
            model.setNewNote(getIntent().getBooleanExtra(IS_NEW_NOTE, false));
        } else {
            model = Parcels.unwrap(savedInstanceState.getParcelable(MODEL));
        }

        presenter.init(model, this);

        titleView.setText(model.getNote().getTitle());
        descriptionView.setText(model.getNote().getDescription());
    }

    @OnFocusChange({R.id.title, R.id.description}) void validateFields() {
        presenter.validateFields(titleView.getText().toString(), descriptionView.getText().toString());
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MODEL, Parcels.wrap(presenter.getModel()));
    }

    public void save(View view) {
        presenter.save(titleView.getText().toString(), descriptionView.getText().toString());
    }

    public void setResultOkAndFinish() {
        setResult(RESULT_OK);
        finish();
    }

    public void populateView(EditNoteModel model) {
        titleView.setError(getErrorMessage(model.getTitleError()));
        descriptionView.setError(getErrorMessage(model.getDescriptionError()));
    }

    private CharSequence getErrorMessage(int res) {
        return getString(res);
    }
}
