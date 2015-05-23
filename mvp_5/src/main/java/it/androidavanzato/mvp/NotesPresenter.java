package it.androidavanzato.mvp;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;
import rx.subscriptions.CompositeSubscription;

public class NotesPresenter {

    private NotesModel model;

    private NotesActivity view;

    private NoteService noteService;

    private final CompositeSubscription connectableSubscriptions = new CompositeSubscription();

    private CompositeSubscription subscriptions = new CompositeSubscription();

    private final List<ObservableWithFactory> observables = new ArrayList<>();

    private Scheduler subscribeOnScheduler;

    private Scheduler observeOnScheduler;

    public NotesPresenter(NoteService noteService, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.noteService = noteService;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    public void init(NotesModel model) {
        this.model = model;
    }

    public NotesModel getModel() {
        return model;
    }

    public NotesActivity getView() {
        return view;
    }

    public void openNote(int position) {
        Note note = model.getNotes().get(position);
        view.openDetail(new EditNoteModel(note, false));
    }

    public void reloadData() {
        subscribe(noteService.getNotes(),
                () -> getView().startLoading(),
                l -> {
                    model.setNotes(l);
                    model.setError(false);
                    view.updateView(model);
                }, throwable -> {
                    model.setError(true);
                    view.updateView(model);
                }
        );
    }

    public void pause() {
        view = null;
        subscriptions.unsubscribe();
        subscriptions = new CompositeSubscription();
    }

    public void subscribe(NotesActivity view) {
        this.view = view;
        for (ObservableWithFactory<?> observableWithFactory : observables) {
            subscribe(observableWithFactory);
        }
        if (model.getNotes().isEmpty() && !model.isError() && observables.isEmpty()) {
            reloadData();
        }
    }

    public void destroy() {
        connectableSubscriptions.unsubscribe();
    }

    private <T> void subscribe(Observable<T> observable, Action0 onAttach, Action1<? super T> onNext, Action1<Throwable> onError) {
        ConnectableObservable<T> replay = observable.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler).replay();
        connectableSubscriptions.add(replay.connect());
        Func0<Subscriber<T>> factory = () -> new Subscriber<T>() {
            @Override public void onStart() {
                if (onAttach != null) {
                    onAttach.call();
                }
            }

            @Override public void onCompleted() {

            }

            @Override public void onError(Throwable e) {
                if (onError != null) {
                    onError.call(e);
                }
            }

            @Override public void onNext(T t) {
                if (onNext != null) {
                    onNext.call(t);
                }
            }
        };
        ObservableWithFactory<T> observableWithFactory = new ObservableWithFactory<>(replay, factory);
        observables.add(observableWithFactory);
        subscribe(observableWithFactory);
    }

    private <T> void subscribe(ObservableWithFactory<T> observableWithFactory) {
        Observable<T> observable = observableWithFactory.observable;
        Subscriber<T> subscriber = observableWithFactory.subscriberFactory.call();
        subscriptions.add(observable.subscribe(
                Actions.empty(),
                t -> observables.remove(observableWithFactory),
                () -> observables.remove(observableWithFactory)));
        subscriptions.add(observable.subscribe(subscriber));
    }
}
