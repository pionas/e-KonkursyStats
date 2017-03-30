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
        void updateData(Object o);

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

        MainActivityMVP.View getView();

        String getMessage();

    }

    interface Model<T> {
        Observable<Article> articleResult();

        Observable<User> usersResult();

        Observable<Error> sendMessage(Object o);

    }
}
