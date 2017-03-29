package info.e_konkursy.stats.Interface;


/**
 * Created by Adrian Pionka on 2017-03-28.
 */

public interface ObservableInterface<T> {
    void addObserver(ObserverInterface<T> observer);
    void removeObserver(ObserverInterface<T> observer);
}
