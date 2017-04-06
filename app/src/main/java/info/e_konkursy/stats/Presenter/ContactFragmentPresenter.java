package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.ContactFragmentMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.ResponseObservableParser;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class ContactFragmentPresenter extends BasePresenter<ContactFragmentMVP.View, ContactFragmentMVP.Model> implements ContactFragmentMVP.Presenter {
    public ContactFragmentPresenter(ContactFragmentMVP.Model model) {
        super(model);
    }

    @Override
    public void itemOnClick(ContactMessage contactMessage) {

    }

    @Override
    public void loadData() {

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
