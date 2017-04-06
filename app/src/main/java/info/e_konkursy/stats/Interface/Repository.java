package info.e_konkursy.stats.Interface;

import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface Repository<T> {
    Observable<T> getData();
}
