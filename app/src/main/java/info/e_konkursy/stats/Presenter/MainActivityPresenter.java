package info.e_konkursy.stats.Presenter;

import android.widget.EditText;

import java.util.ArrayList;

import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.Helpers.KeyboardHelper;
import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Model.ResponseObservableParser;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import info.e_konkursy.stats.Utils.Validation.Validators;
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
    public MainActivityMVP.View getView() {
        return view;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void contactValidate() {
        if (view != null) {
            EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
            EditText editTextMail = (EditText) view.findViewById(R.id.editTextMail);
            EditText editTextMessage = (EditText) view.findViewById(R.id.editTextMessage);
            ArrayList<Validators> arrayList = new ArrayList<>();
            arrayList.add(new Validators(view.getActivity(), editTextName).minLenght(5).maxLenght(50).required());
            arrayList.add(new Validators(view.getActivity(), editTextMail).isMail().required());
            arrayList.add(new Validators(view.getActivity(), editTextMessage).minLenght(5).required());
            boolean validate = true;
            for (Validators v : arrayList) {
                v.getTextView().setError(null);
                try {
                    v.validate();
                } catch (ValidationException e) {
                    v.getTextView().setError(e.getMessage());
                    validate = false;
                    if (view.getActivity().getCurrentFocus() == null) {
                        v.getTextView().requestFocus();
                    }
                }
            }

            if (validate) {
                ContactMessage contactMessage = new ContactMessage();
                contactMessage.setName(editTextName.getText().toString());
                contactMessage.setMail(editTextMail.getText().toString());
                contactMessage.setContent(editTextMessage.getText().toString());
                sendMessage(contactMessage);
                KeyboardHelper.KeyboardHide(view.getActivity(), view.getActivity().getCurrentFocus());
            }
        }
    }


    @Override
    public void sendMessage(ContactMessage contactMessage) {
        if (view != null) {
            view.showDialog();
            message = view.getString(R.string.message_was_send);
        }
        subscription = new ResponseObservableParser(this, model.sendMessage(contactMessage)).getObservable().subscribe();
    }
}
