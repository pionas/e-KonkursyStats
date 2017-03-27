package info.e_konkursy.stats.App;

import javax.inject.Singleton;

import dagger.Component;
import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Module.StatsModule;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

@Singleton
@Component(modules = {StatsModule.class, ApiModuleForStats.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);

}