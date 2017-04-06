package info.e_konkursy.stats.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.ContactFragmentMVP;
import info.e_konkursy.stats.Model.ContactFragmentModel;
import info.e_konkursy.stats.Presenter.ContactFragmentPresenter;
import info.e_konkursy.stats.Repository.ContactRepository;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */

@Module
public class ContactModule {
    @Provides
    public ContactFragmentMVP.Presenter provideContactFragmentPresenter(ContactFragmentMVP.Model model) {
        return new ContactFragmentPresenter(model);
    }

    @Provides
    public ContactFragmentMVP.Model provideContactFragmentModel(ContactRepository repository) {
        return new ContactFragmentModel(repository);
    }

    @Singleton
    @Provides
    public ContactRepository provideRepo(ApiService apiService) {
        return new ContactRepository(apiService);
    }
}
