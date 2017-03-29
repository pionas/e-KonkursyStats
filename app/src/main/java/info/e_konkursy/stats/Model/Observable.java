package info.e_konkursy.stats.Model;

import java.util.ArrayList;

import info.e_konkursy.stats.Interface.ObservableInterface;
import info.e_konkursy.stats.Interface.ObserverInterface;

/**
 * Created by Adrian Pionka on 2017-03-28.
 */

public class Observable<T> implements ObservableInterface<T> {
    private final ArrayList<ObserverInterface<T>> observers = new ArrayList<ObserverInterface<T>>();

    public void addObserver(ObserverInterface<T> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeObserver(ObserverInterface<T> observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    protected void notifyObservers(final T t) {
        synchronized (observers) {
            for (ObserverInterface<T> observer : observers) {
                observer.notify(t);
            }
        }
    }
}