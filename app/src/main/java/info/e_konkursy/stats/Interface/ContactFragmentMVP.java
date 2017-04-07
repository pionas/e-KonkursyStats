package info.e_konkursy.stats.Interface;

import android.support.annotation.VisibleForTesting;

import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public interface ContactFragmentMVP {
    interface View extends BaseView<ContactMessage> {
        void initValidate(android.view.View view);

        @VisibleForTesting
        Presenter getPresenter();
    }

    interface Presenter extends BasePresenter<ContactMessage> {

        void setView(ContactFragmentMVP.View view);

        View getView();

        void sendMessage(ContactMessage contactMessage);

        @VisibleForTesting
        void setModel(Model model);
    }

    interface Model {
        Observable<Error> sendMessage(ContactMessage contactMessage);
    }
}
