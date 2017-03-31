package info.e_konkursy.stats.Model;

import android.util.Log;

import java.net.UnknownHostException;

import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Adrian Pionka on 2017-03-30.
 */

public class ResponseObservableParser {
    private final Observable observable;
    private final MainActivityMVP.Presenter presenter;
    private final MainActivityMVP.View view;

    public ResponseObservableParser(MainActivityMVP.Presenter presenter, Observable observable) {
        this.presenter = presenter;
        this.observable = observable;
        view = presenter.getView();
    }

    private synchronized <T> rx.Observable.Transformer<T, T> applySchedulers() {
        return observable1 -> observable1
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::onNext)
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
