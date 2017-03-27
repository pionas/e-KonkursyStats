package info.e_konkursy.stats.App;

import android.app.Application;

import info.e_konkursy.stats.Module.StatsModule;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class App extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getComponent() {
        if (component == null) {
            component = DaggerApplicationComponent.builder()
                    .statsModule(new StatsModule())
                    .build();
        }
        return component;
    }
}
