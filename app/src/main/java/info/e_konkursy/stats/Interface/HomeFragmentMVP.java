package info.e_konkursy.stats.Interface;

import android.support.annotation.VisibleForTesting;

import info.e_konkursy.stats.Model.POJO.Article;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */

public interface HomeFragmentMVP {
    interface View extends BaseView<Article> {
        void openUrl(String url);

        @VisibleForTesting
        Presenter getPresenter();
    }

    interface Presenter extends BasePresenter<Article> {

        void setView(HomeFragmentMVP.View view);

        View getView();

        @VisibleForTesting
        void setModel(Model model);
    }

    interface Model extends BaseModel<Article> {

    }
}