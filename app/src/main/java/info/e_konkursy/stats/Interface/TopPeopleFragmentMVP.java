package info.e_konkursy.stats.Interface;

import android.support.annotation.VisibleForTesting;

import info.e_konkursy.stats.Model.POJO.User;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public interface TopPeopleFragmentMVP {
    interface View extends BaseView<User> {
        void openUrl(String url);

        @VisibleForTesting
        Presenter getPresenter();
    }

    interface Presenter extends BasePresenter<User> {

        void setView(View view);

        View getView();

        @VisibleForTesting
        void setModel(Model model);
    }

    interface Model extends BaseModel<User> {

    }
}
