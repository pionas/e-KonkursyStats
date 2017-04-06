package info.e_konkursy.stats.Presenter;

import info.e_konkursy.stats.Interface.HomeFragmentMVP;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.ResponseObservableParser;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class HomeFragmenPresenter extends BasePresenter<HomeFragmentMVP.View, HomeFragmentMVP.Model> implements HomeFragmentMVP.Presenter {
    public HomeFragmenPresenter(HomeFragmentMVP.Model model) {
        super(model);
    }


    @Override
    public void itemOnClick(Article article) {

    }

    @Override
    public void loadData() {
        if (view != null) {
            view.showDialog();
        }
        subscription = new ResponseObservableParser(this, model.getResult()).getObservable().subscribe();
    }

}
