package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Model.ResponseObservableParser;
import info.e_konkursy.stats.Utils.Constants;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class TopPeopleFragmentPresenter extends BasePresenter<TopPeopleFragmentMVP.View, TopPeopleFragmentMVP.Model> implements TopPeopleFragmentMVP.Presenter {
    public TopPeopleFragmentPresenter(TopPeopleFragmentMVP.Model model) {
        super(model);
    }

    @Override
    public void itemOnClick(User user) {
        if (view != null) {
            view.openUrl(Constants.BASE_URL + "profile/user/" + user.getUsername());
        }
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = new ResponseObservableParser(this, model.getResult()).getObservable().subscribe();

    }
}
