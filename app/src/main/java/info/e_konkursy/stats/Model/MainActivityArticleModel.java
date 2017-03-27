package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.MainActivityArticleMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityArticleModel implements MainActivityArticleMVP.Model {
    private Repository repository;

    public MainActivityArticleModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Article> result() {
        return repository.getArticlesData();
    }
}
