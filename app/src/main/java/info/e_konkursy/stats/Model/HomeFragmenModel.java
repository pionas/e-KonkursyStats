package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.HomeFragmentMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class HomeFragmenModel implements HomeFragmentMVP.Model {
    private Repository<Article> repository;

    public HomeFragmenModel(Repository<Article> repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Article> getResult() {
        return repository.getData();
    }


}
