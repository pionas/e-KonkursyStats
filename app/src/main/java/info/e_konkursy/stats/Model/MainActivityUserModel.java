package info.e_konkursy.stats.Model;

import info.e_konkursy.stats.Interface.MainActivityUserMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class MainActivityUserModel implements MainActivityUserMVP.Model {
    private Repository repository;

    public MainActivityUserModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<User> result() {
        return repository.getUsersData();
    }
}
