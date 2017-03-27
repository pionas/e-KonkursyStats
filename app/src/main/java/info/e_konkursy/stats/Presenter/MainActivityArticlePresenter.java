package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.MainActivityArticleMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityArticlePresenter implements MainActivityArticleMVP.Presenter {
    private MainActivityArticleMVP.Model model;
    private MainActivityArticleMVP.View view;
    private Subscription subscription = null;

    public MainActivityArticlePresenter(MainActivityArticleMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = model.result().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Article>() {
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
            public void onNext(Article article) {
                if (view != null) {
                    view.updateData(article);
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
    public void setView(MainActivityArticleMVP.View view) {
        this.view = view;
    }
}
