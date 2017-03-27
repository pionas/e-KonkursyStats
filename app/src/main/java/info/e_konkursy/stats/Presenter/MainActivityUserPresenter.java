package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.MainActivityUserMVP;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityUserPresenter implements MainActivityUserMVP.Presenter {
    private MainActivityUserMVP.Model model;
    private MainActivityUserMVP.View view;
    private Subscription subscription = null;

    public MainActivityUserPresenter(MainActivityUserMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = model.result().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null) {
                    view.showSnackbar("Error getting");
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
    public void setView(MainActivityUserMVP.View view) {
        this.view = view;
    }
}
