package info.e_konkursy.stats.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.HomeFragmentMVP;
import info.e_konkursy.stats.Model.HomeFragmenModel;
import info.e_konkursy.stats.Presenter.HomeFragmenPresenter;
import info.e_konkursy.stats.Repository.HomeRepository;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
@Module
public class HomeModule {
    @Provides
    public HomeFragmentMVP.Presenter provideHomeFragmentPresenter(HomeFragmentMVP.Model model) {
        return new HomeFragmenPresenter(model);
    }

    @Provides
    public HomeFragmentMVP.Model provideHomeFragmentModel(HomeRepository repository) {
        return new HomeFragmenModel(repository);
    }

    @Singleton
    @Provides
    public HomeRepository provideRepo(ApiService apiService) {
        return new HomeRepository(apiService);
    }
}
