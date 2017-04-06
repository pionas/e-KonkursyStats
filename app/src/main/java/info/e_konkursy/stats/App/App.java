package info.e_konkursy.stats.App;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import info.e_konkursy.stats.Utils.Constants;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class App extends Application {
    private DaggerHomeComponent.Builder homeComponent;
    private DaggerContactComponent.Builder contactComponent;
    private DaggerTopPeopleComponent.Builder topPeopleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public DaggerHomeComponent.Builder getHomeComponent() {
        if (homeComponent == null) {
            homeComponent = DaggerHomeComponent.builder();
        }
        return homeComponent;
    }

    public DaggerContactComponent.Builder getContactComponent() {
        if (contactComponent == null) {
            contactComponent = DaggerContactComponent.builder();
        }
        return contactComponent;
    }

    public DaggerTopPeopleComponent.Builder getTopPeopleComponent() {
        if (topPeopleComponent == null) {
            topPeopleComponent = DaggerTopPeopleComponent.builder();
        }
        return topPeopleComponent;
    }

    public String getStoragePath() {
        String folderName = Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.LOCAL_STORAGE;
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderName;
    }
}