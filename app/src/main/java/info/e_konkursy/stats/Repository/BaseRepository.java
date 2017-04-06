package info.e_konkursy.stats.Repository;

import java.util.ArrayList;
import java.util.List;

import info.e_konkursy.stats.Interface.ApiService;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public abstract class BaseRepository<T> {
    protected ApiService apiService;
    protected static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds
    protected long repositoryTimestamp;
    protected List<T> list;


    public BaseRepository(ApiService apiService) {
        repositoryTimestamp = System.currentTimeMillis();
        this.apiService = apiService;
        list = new ArrayList<>();
    }

    protected boolean isUpToDate() {
        return System.currentTimeMillis() - repositoryTimestamp < STALE_MS;
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
