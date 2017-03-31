package info.e_konkursy.stats.Helpers;

import android.util.Log;

import com.google.gson.Gson;

import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import retrofit2.http.Body;
import rx.Observable;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by Adrian Pionka on 2017-03-31.
 */

public class ApiServiceMock implements ApiService {
    @Override
    public Observable<LastAdded> getLastAdded() {
        String fileName = "article_list.json";

        try {
            String s = RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName);
            return Observable.just(new Gson().fromJson(s, LastAdded.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Observable<TopUsers> getTopUsers() {
        String fileName = "top_people.json";

        try {
            String s = RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName);
            return Observable.just(new Gson().fromJson(s, TopUsers.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Observable<Contact> sendMessage(@Body ContactMessage contactMessage) {
        return null;
    }
}
