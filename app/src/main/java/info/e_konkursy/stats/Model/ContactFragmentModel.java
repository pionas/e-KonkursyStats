package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.ContactFragmentMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.Error;
import info.e_konkursy.stats.Repository.ContactRepository;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class ContactFragmentModel implements ContactFragmentMVP.Model {
    private ContactRepository repository;

    public ContactFragmentModel(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Error> sendMessage(ContactMessage contactMessage) {
        return repository.send(contactMessage);
    }
}