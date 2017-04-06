package info.e_konkursy.stats.Repository;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class ContactRepository {
    protected ApiService apiService;

    public ContactRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Error> send(ContactMessage contactMessage) {
        Observable<Contact> contactObservable = apiService.sendMessage(contactMessage);
        return contactObservable.concatMap(new Func1<Contact, Observable<Error>>() {
            @Override
            public Observable<Error> call(Contact contact) {
                return Observable.just(contact.getError());
            }
        });

    }

}
