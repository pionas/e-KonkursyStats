package info.e_konkursy.stats.Interface;

import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public interface BaseModel<T> {
    Observable<T> getResult();
}
