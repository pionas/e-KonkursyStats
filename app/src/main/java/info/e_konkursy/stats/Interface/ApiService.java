package info.e_konkursy.stats.Interface;

import info.e_konkursy.stats.Model.POJO.Contact;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Model.POJO.LastAdded;
import info.e_konkursy.stats.Model.POJO.TopUsers;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public interface ApiService {
    @GET("articles_last_added")
    Observable<LastAdded> getLastAdded();

    @GET("get_top_users")
    Observable<TopUsers> getTopUsers();

    @POST("contact")
    Observable<Contact> sendMessage(@Body ContactMessage contactMessage);
}
