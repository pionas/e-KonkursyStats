package info.e_konkursy.stats.Interface;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface MainActivityMVP {
    interface View {
        void updateData(Article article);

        void updateData(User user);

        void showSnackbar(String msg);

        void showDialog();

        void hideDialog();

        void openUrl(String url);

        MainActivity getActivity();

        void initValidate(android.view.View view);

    }

    interface Presenter {
        void loadArticleData();

        void loadUserData();

        void rxUnsubscribe();

        void setView(MainActivityMVP.View view);

        void itemOnClick(User user);

        void sendMessage(ContactMessage contactMessage);

    }

    interface Model {
        Observable<Article> articleResult();

        Observable<User> usersResult();

        Observable<Error> sendMessage(ContactMessage contactMessage);

    }
}
