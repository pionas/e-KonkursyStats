package info.e_konkursy.stats.Helpers;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Adrian Pionka on 28 marzec 2017
 * adrian@pionka.com
 */
public class PermissionsHelper {

    public static ArrayList<String> getPermissionList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Manifest.permission.INTERNET);
        arrayList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            arrayList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        return arrayList;
    }
}
