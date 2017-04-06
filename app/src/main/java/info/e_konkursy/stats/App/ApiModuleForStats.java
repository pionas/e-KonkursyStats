package info.e_konkursy.stats.App;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Utils.Constants;
import info.e_konkursy.stats.Utils.Environment;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiModuleForStats is a module whose used to get data
 * MainActivityTest is a test for MainActivity
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */

@Module
public class ApiModuleForStats {
    @Provides
    public OkHttpClient provideClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Environment.showLog ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public ApiService provideApiService() {
        return provideRetrofit(info.e_konkursy.stats.Utils.Environment.apiUrl, provideClient()).create(ApiService.class);
    }
}