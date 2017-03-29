package info.e_konkursy.stats.Interface;

public interface ObserverInterface<T> {
    void notify(T model);
}
