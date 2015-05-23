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

    public static final String MODEL = "model";

    @InjectView(R.id.title) EditText titleView;

    @InjectView(R.id.description) EditText descriptionView;

    @Inject EditNotePresenter presenter;

    public static void openDetail(Activity activity, EditNoteModel model) {
        Intent intent = new Intent(activity, EditNoteActivity.class);
        populateIntent(intent, model);
        activity.startActivityForResult(intent, 1);
    }

    public static Intent populateIntent(Intent intent, EditNoteModel model) {
        intent.putExtra(MODEL, Parcels.wrap(model));
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NoteApplication) getApplicationContext()).getComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        Bundle b = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        EditNoteModel model = Parcels.unwrap(b.getParcelable(MODEL));
        presenter.init(model, this);

        titleView.setText(model.getNote().getTitle());
        descriptionView.setText(model.getNote().getDescription());
    }

    @OnFocusChange({R.id.title, R.id.description}) void validateFields() {
        presenter.validateFields(titleView.getText().toString(), descriptionView.getText().toString());
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("model", Parcels.wrap(presenter.getModel()));
    }

    public void save(View view) {
        presenter.save(titleView.getText().toString(), descriptionView.getText().toString());
    }

    public void setResultOkAndFinish() {
        setResult(RESULT_OK);
        finish();
    }

    public void populateView(EditNoteModel model) {
        titleView.setError(model.isTitleWithError() ? "Mandatory field" : null);
        descriptionView.setError(model.isDescriptionWithError() ? "Mandatory field" : null);
    }
}
