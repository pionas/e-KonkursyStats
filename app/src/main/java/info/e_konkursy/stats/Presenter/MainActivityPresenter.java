package info.e_konkursy.stats.Presenter;

import android.content.Context;

import java.net.UnknownHostException;

import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private final Context context;
    private MainActivityMVP.Model model;
    private MainActivityMVP.View view;
    private Subscription subscription = null;

    public MainActivityPresenter(Context context, MainActivityMVP.Model model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public void loadArticleData() {
        if (view != null) {
            view.showDialog();
        }

        subscription = model.articleResult()
                .compose(applySchedulers(null))
                .doOnNext(article -> {
                    if (view != null) {
                        view.updateData(article);
                    }
                })
                .subscribe();
    }

    @Override
    public void loadUserData() {
        if (view != null) {
            view.showDialog();
        }

        subscription = model.usersResult()
                .compose(applySchedulers(null))
                .doOnNext(user -> {
                    if (view != null) {
                        view.updateData(user);
                    }
                })
                .subscribe();
    }


    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void setView(MainActivityMVP.View view) {
        this.view = view;
    }


    @Override
    public void itemOnClick(User user) {
        if (view != null) {
            view.openUrl(Constants.BASE_URL + "profile/user/" + user.getUsername());
        }
    }

    @Override
    public void sendMessage(ContactMessage contactMessage) {
        if (view != null) {
            view.showDialog();
        }

        subscription = model.sendMessage(contactMessage)
                .compose(applySchedulers(context.getString(R.string.message_was_send)))
                .subscribe();
    }

    private void onComplete(String message) {
        if (view != null) {
            view.hideDialog();
            if (message != null) {
                view.showSnackbar(message);
            }
        }
    }

    private <T> Observable.Transformer<T, T> applySchedulers(String message) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::showError)
                        .doOnCompleted(() -> onComplete(message));
            }

            private void showError(Throwable e) {
                if (view != null) {
                    String errorMessage = e.getMessage();
                    if (e instanceof UnknownHostException) {
                        errorMessage = context.getString(R.string.no_internet_connection);
                    }
                    view.hideDialog();
                    view.showSnackbar(errorMessage);
                }
            }
        };
    }

}
