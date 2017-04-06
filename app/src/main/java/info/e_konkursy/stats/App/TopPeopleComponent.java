package info.e_konkursy.stats.App;

import javax.inject.Singleton;

import dagger.Component;
import info.e_konkursy.stats.Fragment.ContactFragment;
import info.e_konkursy.stats.Fragment.TopPeopleFragment;
import info.e_konkursy.stats.Module.HomeModule;
import info.e_konkursy.stats.Module.TopPeopleModule;

/**
 * Created by Adrian Pionka on 06 kwiecień 2017
 * adrian@pionka.com
 */

@Singleton
@Component(modules = {TopPeopleModule.class, ApiModuleForStats.class})
public interface TopPeopleComponent {

    void inject(TopPeopleFragment topPeopleFragment);

}
