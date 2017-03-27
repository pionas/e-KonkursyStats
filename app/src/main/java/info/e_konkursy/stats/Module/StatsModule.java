package info.e_konkursy.stats.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.MainActivityArticleMVP;
import info.e_konkursy.stats.Interface.MainActivityUserMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.MainActivityArticleModel;
import info.e_konkursy.stats.Model.MainActivityUserModel;
import info.e_konkursy.stats.Presenter.MainActivityArticlePresenter;
import info.e_konkursy.stats.Presenter.MainActivityUserPresenter;
import info.e_konkursy.stats.Repository.StatsRepository;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

@Module
public class StatsModule {
    @Provides
    public MainActivityArticleMVP.Presenter provideMainActivityArticlePresenter(MainActivityArticleMVP.Model model) {
        return new MainActivityArticlePresenter(model);
    }

    @Provides
    public MainActivityArticleMVP.Model provideMainActivityArticleModel(Repository repository) {
        return new MainActivityArticleModel(repository);
    }

    @Provides
    public MainActivityUserMVP.Presenter provideMainActivityUserPresenter(MainActivityUserMVP.Model model) {
        return new MainActivityUserPresenter(model);
    }

    @Provides
    public MainActivityUserMVP.Model provideMainActivityUserModel(Repository repository) {
        return new MainActivityUserModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(ApiService apiService) {
        return new StatsRepository(apiService);
    }
}
