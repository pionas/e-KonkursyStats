package info.e_konkursy.stats.App;

import javax.inject.Singleton;

import dagger.Component;
import info.e_konkursy.stats.Fragment.HomeFragment;
import info.e_konkursy.stats.Module.HomeModule;

/**
 * Created by Adrian Pionka on 06 kwiecień 2017
 * adrian@pionka.com
 */

@Singleton
@Component(modules = {HomeModule.class, ApiModuleForStats.class})
public interface HomeComponent {

    void inject(HomeFragment homeFragment);

}
