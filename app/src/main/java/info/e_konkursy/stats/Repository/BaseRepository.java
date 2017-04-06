package info.e_konkursy.stats.Repository;

import java.util.ArrayList;
import java.util.List;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Utils.Environment;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public abstract class BaseRepository<T> {
    protected ApiService apiService;
    protected long repositoryTimestamp;
    protected List<T> list;


    public BaseRepository(ApiService apiService) {
        repositoryTimestamp = System.currentTimeMillis();
        this.apiService = apiService;
        list = new ArrayList<>();
    }

    protected boolean isUpToDate() {
        return System.currentTimeMillis() - repositoryTimestamp < Environment.CACHE_MAX_LIFETIME_IN_MILLIS;
    }

    public Observable<T> getFromMemory() {

        if (isUpToDate()) {
            return Observable.from(list);
        } else {
            repositoryTimestamp = System.currentTimeMillis();
            list.clear();
            return Observable.empty();
        }
    }

    public abstract Observable<T> getFromNetwork();

    public Observable<T> getData() {
        return getFromMemory().switchIfEmpty(getFromNetwork());
    }

}
