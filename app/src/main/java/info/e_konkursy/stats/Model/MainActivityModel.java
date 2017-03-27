package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityModel implements MainActivityMVP.Model {
    private Repository repository;

    public MainActivityModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Article> articleResult() {
        return repository.getArticlesData();
    }

    @Override
    public Observable<User> usersResult() {
        return repository.getUsersData();
    }

    @Override
    public Observable<Error> sendMessage(ContactMessage contactMessage) {
        return repository.sendMessage(contactMessage);
    }
}
