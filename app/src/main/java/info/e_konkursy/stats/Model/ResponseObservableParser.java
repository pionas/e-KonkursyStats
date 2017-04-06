package info.e_konkursy.stats.Model;

import java.net.UnknownHostException;

import info.e_konkursy.stats.Interface.BaseView;
import info.e_konkursy.stats.Presenter.BasePresenter;
import info.e_konkursy.stats.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ResponseObservableParser add standard opertation to any Observable
 * Created by Adrian Pionka on 30 marzec 2017
 * adrian@pionka.com
 */
public class ResponseObservableParser {
    private final Observable observable;
    private final BasePresenter presenter;
    private final BaseView view;

    public ResponseObservableParser(BasePresenter presenter, Observable observable) {
        this.presenter = presenter;
        this.observable = observable;
        view = (BaseView) presenter.getView();
    }

    private synchronized <T> rx.Observable.Transformer<T, T> applySchedulers() {
        return observable1 -> observable1
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::onNext)
                .onErrorReturn(throwable -> {
                    showError(throwable);
                    return null;
                })
                .doOnError(this::showError)
                .doOnCompleted(this::onComplete);
    }

    private <T> void onNext(T t) {
        if (view != null) {
            view.updateData(t);
        }
    }

    private void onComplete() {
        String message = presenter.getMessage();
        if (view != null) {
            view.hideDialog();
            if (message != null) {
                view.showSnackbar(message);
            }
        }
    }

    private void showError(Throwable e) {
        if (view != null) {
            String errorMessage = e.getMessage();
            if (e instanceof UnknownHostException) {
                errorMessage = view.getString(R.string.no_internet_connection);
            }
            view.hideDialog();
            view.showSnackbar(errorMessage);
        }
    }

    public Observable getObservable() {
        return observable.compose(applySchedulers());
    }
}
