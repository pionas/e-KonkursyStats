package info.e_konkursy.stats.Module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Interface.Repository;
import info.e_konkursy.stats.Model.MainActivityModel;
import info.e_konkursy.stats.Presenter.MainActivityPresenter;
import info.e_konkursy.stats.Repository.StatsRepository;
import info.e_konkursy.stats.Validators.ContactValidator;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

@Module
public class StatsModule {
    protected final MainActivityMVP.View view;

    public StatsModule(MainActivityMVP.View view) {
        this.view = view;
    }

    @Provides
    public Context provideContext() {
        return view.getActivity();
    }

    @Provides
    public ContactValidator provideContactValidator(MainActivityMVP.Presenter presenter) {
        return new ContactValidator(view, presenter);
    }

    @Provides
    public MainActivityMVP.Presenter provideMainActivityPresenter(MainActivityMVP.Model model) {
        return new MainActivityPresenter(model);
    }

    @Provides
    public MainActivityMVP.Model provideMainActivityModel(Repository repository) {
        return new MainActivityModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(ApiService apiService) {
        return new StatsRepository(apiService);
    }
}
