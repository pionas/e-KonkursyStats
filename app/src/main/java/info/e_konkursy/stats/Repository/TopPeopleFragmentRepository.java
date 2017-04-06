package info.e_konkursy.stats.Repository;

import android.util.Log;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import info.e_konkursy.stats.Model.POJO.User;
import rx.Observable;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class TopPeopleFragmentRepository extends BaseRepository<User> implements Repository<User> {
    public TopPeopleFragmentRepository(ApiService apiService) {
        super(apiService);
        Log.d("aaaaaaa", "TopPeopleFragmentRepository");
    }

    @Override
    public Observable<User> getFromNetwork() {
        Observable<TopUsers> topUsersObservable = apiService.getTopUsers();

        return topUsersObservable
                .concatMap(topUsers -> Observable.from(topUsers.getPeopleInfo()))
                .doOnNext(user -> list.add(user));
    }
}
