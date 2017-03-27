package info.e_konkursy.stats.Interface;

import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface Repository {
    Observable<Article> getArticlesData();

    Observable<Article> getArticleFromMemory();

    Observable<Article> getArticleFromNetwork();

    Observable<User> getUsersData();

    Observable<User> getUsersFromMemory();

    Observable<User> getUsersromNetwork();
}
