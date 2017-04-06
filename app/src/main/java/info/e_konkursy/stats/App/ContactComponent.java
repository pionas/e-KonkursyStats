package info.e_konkursy.stats.App;

import javax.inject.Singleton;

import dagger.Component;
import info.e_konkursy.stats.Fragment.ContactFragment;
import info.e_konkursy.stats.Module.ContactModule;

/**
 * Created by Adrian Pionka on 06 kwiecień 2017
 * adrian@pionka.com
 */

@Singleton
@Component(modules = {ContactModule.class, ApiModuleForStats.class})
public interface ContactComponent {

    void inject(ContactFragment contactFragment);

}
