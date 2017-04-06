package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class TopPeopleFragmentModel implements TopPeopleFragmentMVP.Model {
    private Repository<User> repository;

    public TopPeopleFragmentModel(Repository<User> repository) {
        this.repository = repository;
    }

    @Override
    public Observable<User> getResult() {
        return repository.getData();
    }
}
