package it.androidavanzato.mvp;

import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class EditNoteView {
    private final EditNoteActivity activity;

    private final EditNotePresenter presenter;

    @InjectView(R.id.title) EditText titleView;

    @InjectView(R.id.description) EditText descriptionView;

    public EditNoteView(EditNoteActivity activity, EditNotePresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @OnFocusChange({R.id.title, R.id.description}) void validateFields() {
        presenter.validateFields(titleView.getText().toString(), descriptionView.getText().toString());
    }

    @OnClick(R.id.save) public void save() {
        presenter.save(titleView.getText().toString(), descriptionView.getText().toString());
    }

    public void populateView(EditNoteModel model) {
        titleView.setText(model.getNote().getTitle());
        descriptionView.setText(model.getNote().getDescription());
        titleView.setError(getErrorMessage(model.getTitleError()));
        descriptionView.setError(getErrorMessage(model.getDescriptionError()));
    }

    private CharSequence getErrorMessage(int error) {
        return error > 0 ? activity.getErrorMessage(error) : null;
    }

    public void setResultOkAndFinish() {
        activity.setResultOkAndFinish();
    }
}
