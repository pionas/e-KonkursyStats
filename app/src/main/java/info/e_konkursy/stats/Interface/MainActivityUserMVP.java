package info.e_konkursy.stats.Interface;

import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface MainActivityUserMVP {
    interface View {
        void updateData(User user);

        void showSnackbar(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Presenter {
        void loadData();

        void rxUnsubscribe();

        void setView(MainActivityUserMVP.View view);
    }

    interface Model {
        Observable<User> result();
    }
}
