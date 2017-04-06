package info.e_konkursy.stats.Repository;

import java.io.File;

import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Helpers.DateHelper;
import info.e_konkursy.stats.Helpers.FileHelper;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Utils.Constants;
import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class HomeRepository extends BaseRepository<Article> implements Repository<Article> {

    public HomeRepository(ApiService apiService) {
        super(apiService);
    }


    @Override
    public Observable<Article> getFromNetwork() {
        Observable<LastAdded> lastAddedObservable = apiService.getLastAdded();

        return lastAddedObservable
                .concatMap(lastAdded -> Observable.from(lastAdded.getArticles()))
                .doOnNext(article -> list.add(article))
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

}
