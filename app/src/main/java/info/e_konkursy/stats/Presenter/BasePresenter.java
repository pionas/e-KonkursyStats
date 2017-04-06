package info.e_konkursy.stats.Presenter;

import rx.Subscription;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public abstract class BasePresenter<V, M> {
    protected Subscription subscription;
    protected String message;
    protected V view;
    protected M model;

    BasePresenter(M model) {
        this.model = model;
    }

    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public void setView(V v) {
        this.view = v;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public V getView() {
        return view;
    }
}
