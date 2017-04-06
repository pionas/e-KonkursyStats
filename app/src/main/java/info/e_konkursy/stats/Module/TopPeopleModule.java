package info.e_konkursy.stats.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Model.TopPeopleFragmentModel;
import info.e_konkursy.stats.Presenter.TopPeopleFragmentPresenter;
import info.e_konkursy.stats.Repository.TopPeopleFragmentRepository;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
@Module
public class TopPeopleModule {
    @Provides
    public TopPeopleFragmentMVP.Presenter provideHomeFragmentPresenter(TopPeopleFragmentMVP.Model model) {
        return new TopPeopleFragmentPresenter(model);
    }

    @Provides
    public TopPeopleFragmentMVP.Model provideHomeFragmentModel(TopPeopleFragmentRepository repository) {
        return new TopPeopleFragmentModel(repository);
    }

    @Singleton
    @Provides
    public TopPeopleFragmentRepository provideRepo(ApiService apiService) {
        return new TopPeopleFragmentRepository(apiService);
    }
}
