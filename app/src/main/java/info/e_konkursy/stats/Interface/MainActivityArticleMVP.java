package info.e_konkursy.stats.Interface;

import info.e_konkursy.stats.Model.POJO.Article;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface MainActivityArticleMVP {
    interface View {
        void updateData(Article article);

        void showSnackbar(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Presenter {
        void loadData();

        void rxUnsubscribe();

        void setView(MainActivityArticleMVP.View view);
    }

    interface Model {
        Observable<Article> result();
    }
}
