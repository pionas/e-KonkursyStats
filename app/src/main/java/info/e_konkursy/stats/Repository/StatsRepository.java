package info.e_konkursy.stats.Repository;

import java.util.ArrayList;
import java.util.List;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class StatsRepository implements Repository {
    private ApiService apiService;
    private List<Article> articleList;
    private List<User> userList;
    private long articleTimestamp;
    private long userTimestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public StatsRepository(ApiService apiService) {
        this.articleTimestamp = this.userTimestamp = System.currentTimeMillis();
        this.apiService = apiService;
        articleList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    private boolean isArticleUpToDate() {
        return System.currentTimeMillis() - articleTimestamp < STALE_MS;
    }

    private boolean isUserUpToDate() {
        return System.currentTimeMillis() - articleTimestamp < STALE_MS;
    }

    @Override
    public Observable<Article> getArticleFromMemory() {

        if (isArticleUpToDate()) {
            return Observable.from(articleList);
        } else {
            articleTimestamp = System.currentTimeMillis();
            articleList.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Article> getArticleFromNetwork() {
        Observable<LastAdded> topRatedObservable = apiService.getLastAdded();

        return topRatedObservable.concatMap(new Func1<LastAdded, Observable<Article>>() {
            @Override
            public Observable<Article> call(LastAdded lastAdded) {
                return Observable.from(lastAdded.getArticles());
            }
        }).doOnNext(new Action1<Article>() {
            @Override
            public void call(Article article) {
                articleList.add(article);
            }
        });
    }

    @Override
    public Observable<Article> getArticlesData() {
        return getArticleFromMemory().switchIfEmpty(getArticleFromNetwork());
    }


    @Override
    public Observable<User> getUsersFromMemory() {
        if (isUserUpToDate()) {
            return Observable.from(userList);
        } else {
            userTimestamp = System.currentTimeMillis();
            userList.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<User> getUsersromNetwork() {
        Observable<TopUsers> topRatedObservable = apiService.getTopUsers();

        return topRatedObservable.concatMap(new Func1<TopUsers, Observable<User>>() {
            @Override
            public Observable<User> call(TopUsers topUsers) {
                return Observable.from(topUsers.getPeopleInfo());
            }
        }).doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {
                userList.add(user);
            }
        });
    }


    @Override
    public Observable<User> getUsersData() {
        return getUsersFromMemory().switchIfEmpty(getUsersromNetwork());
    }

    @Override
    public Observable<Error> sendMessage(ContactMessage contactMessage) {
        Observable<Contact> contactObservable = apiService.sendMessage(contactMessage);

        return contactObservable.concatMap(new Func1<Contact, Observable<Error>>() {
            @Override
            public Observable<Error> call(Contact contact) {
                return Observable.just(contact.getError());
            }
        });
    }

}
