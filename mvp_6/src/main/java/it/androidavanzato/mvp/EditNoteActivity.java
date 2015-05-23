package it.androidavanzato.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class EditNoteActivity extends ActionBarActivity {

    public static final String MODEL = "model";

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
        View layout = getLayoutInflater().inflate(R.layout.activity_detail, null);
        setContentView(layout);

        Bundle b = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        EditNoteModel model = Parcels.unwrap(b.getParcelable(MODEL));
        EditNoteView view = new EditNoteView(this, presenter);
        ButterKnife.inject(view, layout);
        presenter.init(model, view);
        view.populateView(model);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MODEL, Parcels.wrap(presenter.getModel()));
    }

    public void setResultOkAndFinish() {
        setResult(RESULT_OK);
        finish();
    }

    public CharSequence getErrorMessage(int res) {
        return getString(res);
    }
}
