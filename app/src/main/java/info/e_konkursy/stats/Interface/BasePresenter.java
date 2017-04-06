package info.e_konkursy.stats.Interface;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public interface BasePresenter<T> {
    void itemOnClick(T t);

    void loadData();

    void rxUnsubscribe();

    String getMessage();
}
