package info.e_konkursy.stats.Presenter;

import android.content.Context;

import java.net.UnknownHostException;

import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Contants;
import rx.Observer;
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
        subscription = model.articleResult().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Article>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = "Error getting";
                if (e instanceof UnknownHostException) {
                    errorMessage = context.getString(R.string.no_internet_connection);
                }
                if (view != null) {
                    view.showSnackbar(errorMessage);
                    view.hideDialog();
                }
            }

            @Override
            public void onNext(Article article) {
                if (view != null) {
                    view.updateData(article);
                }
            }
        });
    }

    @Override
    public void loadUserData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = model.usersResult().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = "Error getting";
                if (e instanceof UnknownHostException) {
                    errorMessage = context.getString(R.string.no_internet_connection);
                }
                if (view != null) {
                    view.showSnackbar(errorMessage);
                    view.hideDialog();
                }
            }

            @Override
            public void onNext(User user) {
                if (view != null) {
                    view.updateData(user);
                }
            }
        });
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
            view.openUrl(Contants.BASE_URL + "profile/user/" + user.getUsername());
        }
    }

    @Override
    public void sendMessage(ContactMessage contactMessage) {
        if (view != null) {
            view.showDialog();
        }
        subscription = model.sendMessage(contactMessage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Error>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideDialog();
                    view.showSnackbar(context.getString(R.string.message_was_send));
                }
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = e.getMessage();
                if (e instanceof UnknownHostException) {
                    errorMessage = context.getString(R.string.no_internet_connection);
                }
                if (view != null) {
                    view.hideDialog();
                    view.showSnackbar(errorMessage);
                }
            }

            @Override
            public void onNext(Error error) {

            }
        });
    }


}
