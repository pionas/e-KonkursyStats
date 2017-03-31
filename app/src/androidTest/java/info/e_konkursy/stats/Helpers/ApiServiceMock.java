package info.e_konkursy.stats.Helpers;

import com.google.gson.Gson;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import info.e_konkursy.stats.Utils.RestServiceTestHelper;
import retrofit2.http.Body;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-31.
 */

public class ApiServiceMock implements ApiService {
    @Override
    public Observable<LastAdded> getLastAdded() {
        String fileName = "article_list.json";

        String s = RestServiceTestHelper.getStringFromFile(fileName);
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Observable.just(new Gson().fromJson(s, LastAdded.class));
    }

    @Override
    public Observable<TopUsers> getTopUsers() {
        String fileName = "top_people.json";

        String s = RestServiceTestHelper.getStringFromFile(fileName);
        if (s == null || s.isEmpty()) {
            return null;
        }
        return Observable.just(new Gson().fromJson(s, TopUsers.class));

    }

    @Override
    public Observable<Contact> sendMessage(@Body ContactMessage contactMessage) {
        return null;
    }
}
