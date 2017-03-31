package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Model.ResponseObservableParser;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import rx.Subscription;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private MainActivityMVP.Model model;
    private MainActivityMVP.View view;
    private Subscription subscription = null;
    private String message;

    public MainActivityPresenter(MainActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadArticleData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = new ResponseObservableParser(this, model.articleResult()).getObservable().subscribe();
    }

    @Override
    public void loadUserData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = new ResponseObservableParser(this, model.usersResult()).getObservable().subscribe();

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
            view.openUrl(Constants.BASE_URL + "profile/user/" + user.getUsername());
        }
    }

    @Override
    public void sendMessage(ContactMessage contactMessage) {
        if (view != null) {
            view.showDialog();
            message = view.getActivity().getString(R.string.message_was_send);
        }
        subscription = new ResponseObservableParser(this, model.sendMessage(contactMessage)).getObservable().subscribe();
    }

    @Override
    public MainActivityMVP.View getView() {
        return view;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
