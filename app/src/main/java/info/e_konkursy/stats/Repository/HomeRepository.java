package info.e_konkursy.stats.Repository;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.Article;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import rx.Observable;

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
                .doOnNext(article -> list.add(article));
    }

}
