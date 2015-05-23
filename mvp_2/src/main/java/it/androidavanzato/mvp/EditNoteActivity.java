package it.androidavanzato.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.EditText;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class EditNoteActivity extends ActionBarActivity {

    private static final String NOTE = "note";
    private static final String IS_NEW_NOTE = "isNewNote";
    public static final String MODEL = "model";

    @InjectView(R.id.title) EditText titleView;

    @InjectView(R.id.description) EditText descriptionView;

    @Inject NoteService noteService;

    private EditNoteModel model;

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

        if (savedInstanceState == null) {
            model = new EditNoteModel();
            Note note = Parcels.unwrap(getIntent().getParcelableExtra(NOTE));
            model.setNote(note);
            model.setNewNote(getIntent().getBooleanExtra(IS_NEW_NOTE, false));
        } else {
            model = Parcels.unwrap(savedInstanceState.getParcelable(MODEL));
        }

        titleView.setText(model.getNote().getTitle());
        descriptionView.setText(model.getNote().getDescription());
    }

    @OnFocusChange({R.id.title, R.id.description}) void validateFields() {
        if (model.isSaveExecuted()) {
            validate();
        }
    }

    private boolean validate() {
        boolean isFormValid = true;
        titleView.setError(null);
        descriptionView.setError(null);
        if (TextUtils.isEmpty(titleView.getText())) {
            titleView.setError(getString(R.string.mandatory_field));
            isFormValid = false;
        }
        if (TextUtils.isEmpty(descriptionView.getText())) {
            descriptionView.setError(getString(R.string.mandatory_field));
            isFormValid = false;
        }
        return isFormValid;
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MODEL, Parcels.wrap(model));
    }

    @OnClick(R.id.save) void save() {
        model.setSaveExecuted(true);
        if (validate()) {
            populateFromView();
            noteService.save(model.getNote(), model.isNewNote());
            setResult(RESULT_OK);
            finish();
        }
    }

    private void populateFromView() {
        model.getNote().setTitle(titleView.getText().toString());
        model.getNote().setDescription(descriptionView.getText().toString());
    }
}
