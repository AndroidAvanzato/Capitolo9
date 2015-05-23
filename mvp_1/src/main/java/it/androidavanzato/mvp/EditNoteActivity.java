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
    public static final String SAVE_EXECUTED = "saveExecuted";

    @InjectView(R.id.title) EditText titleView;

    @InjectView(R.id.description) EditText descriptionView;

    private Note note;

    private boolean isNewNote;

    @Inject NoteService noteService;

    private boolean saveExecuted;

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
            note = Parcels.unwrap(getIntent().getParcelableExtra(NOTE));
        } else {
            note = Parcels.unwrap(savedInstanceState.getParcelable(NOTE));
            saveExecuted = savedInstanceState.getBoolean("saveExecuted", false);
        }
        isNewNote = getIntent().getBooleanExtra(IS_NEW_NOTE, false);

        titleView.setText(note.getTitle());
        descriptionView.setText(note.getDescription());
    }

    @OnFocusChange({R.id.title, R.id.description}) void validateFields() {
        if (saveExecuted) {
            validate();
        }
    }

    private boolean validate() {
        boolean isFormValid = true;
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
        outState.putParcelable(NOTE, Parcels.wrap(note));
        outState.putBoolean(SAVE_EXECUTED, saveExecuted);
    }

    @OnClick(R.id.save) void save() {
        saveExecuted = true;
        if (validate()) {
            note.setTitle(titleView.getText().toString());
            note.setDescription(descriptionView.getText().toString());
            noteService.save(note, isNewNote);
            setResult(RESULT_OK);
            finish();
        }
    }

}
