package info.e_konkursy.stats.Repository;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Helpers.DateHelper;
import info.e_konkursy.stats.Helpers.FileHelper;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import info.e_konkursy.stats.Model.POJO.User;
import info.e_konkursy.stats.Utils.Constants;
import rx.Observable;
import rx.exceptions.OnErrorThrowable;
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
        return System.currentTimeMillis() - userTimestamp < STALE_MS;
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
        Observable<LastAdded> lastAddedObservable = apiService.getLastAdded();

        return lastAddedObservable
                .concatMap(lastAdded -> Observable.from(lastAdded.getArticles()))
                .doOnNext(article -> articleList.add(article))
                .flatMap(new Func1<Article, Observable<Article>>() {
                    @Override
                    public Observable<Article> call(Article article) {
                        if (article.getImage() == null || article.getImage().isEmpty()) {
                            return Observable.just(article);
                        }

                        File file = new File(new App().getStoragePath() + File.separator + article.getImage());
                        if (file.exists()) {
                            return Observable.just(article);
                        }

                        String url = DateHelper.changeFormatDate("yyyy/MM/dd/", article.getDateAdd());
                        String imageUri = Constants.APP_ARTICLE_IMAGE + url + article.getImage();

                        try {
                            FileHelper.downloadFile(imageUri, file);
                        } catch (Exception e) {
                            throw OnErrorThrowable.from(OnErrorThrowable.addValueAsLastCause(e, article));
                        }

                        return Observable.just(article);
                    }
                })
                ;
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
    public Observable<User> getUsersNetwork() {
        Observable<TopUsers> topUsersObservable = apiService.getTopUsers();

        return topUsersObservable
                .concatMap(topUsers -> Observable.from(topUsers.getPeopleInfo()))
                .doOnNext(user -> userList.add(user));
    }


    @Override
    public Observable<User> getUsersData() {
        return getUsersFromMemory().switchIfEmpty(getUsersNetwork());
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
