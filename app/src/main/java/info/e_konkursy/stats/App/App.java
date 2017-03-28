package info.e_konkursy.stats.App;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Module.StatsModule;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class App extends Application {
    private DaggerApplicationComponent.Builder component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DaggerApplicationComponent.Builder getComponent() {
        if (component == null) {
            component = DaggerApplicationComponent.builder();
        }
        return component;
    }

    public String getStoragePath() {
        String folderName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/stats/";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderName;
    }
}
