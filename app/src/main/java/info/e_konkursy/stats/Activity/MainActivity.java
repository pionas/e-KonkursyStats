package info.e_konkursy.stats.Activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Fragment.BaseFragment;
import info.e_konkursy.stats.Fragment.ContactFragment;
import info.e_konkursy.stats.Fragment.HomeFragment;
import info.e_konkursy.stats.Fragment.TopPeopleFragment;
import info.e_konkursy.stats.Helpers.PermissionsHelper;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Constants;
import info.e_konkursy.stats.Utils.DialogManager;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ViewGroup rootView;

    private ArrayList<String> persmissions;
    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        persmissions = PermissionsHelper.getPermissionList();

        initListener();
        if (!requestPermissions()) {
            return;
        }
        initView();
    }

    private void initListener() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void initView() {
        if (fragment == null) {
            fragment = HomeFragment.newInstance();
            loadFragment();
        }

        if (navigation == null || navigation.getChildCount() == 0) {
            return;

        }
        navigation.getChildAt(0).setSelected(true);

    }

    private void loadFragment() {
        Log.d("aaaaaaa", "loadFragment=" + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = HomeFragment.newInstance();
                loadFragment();
                return true;
            case R.id.navigation_users:
                fragment = TopPeopleFragment.newInstance();
                loadFragment();
                return true;
            case R.id.navigation_contact:
                fragment = ContactFragment.newInstance();
                loadFragment();
                return true;
        }
        return false;
    }

    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String persmission : persmissions) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), persmission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(persmission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), Constants.REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_ASK_PERMISSIONS:
                Map<String, Integer> perms = new HashMap<>();

                for (String persmission : persmissions) {
                    perms.put(persmission, PackageManager.PERMISSION_GRANTED);
                }

                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                    }

                    boolean startActivity = true;
                    for (String persmission : persmissions) {
                        if (perms.get(persmission) != PackageManager.PERMISSION_GRANTED) {
                            startActivity = false;
                        }
                    }
                    if (startActivity) {
                        initView();
                    } else {

                        boolean showDialog = false;
                        for (String persmission : persmissions) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, persmission)) {
                                showDialog = true;
                            }
                        }

                        if (showDialog) {
                            DialogManager.showPermissionsDialog(
                                    MainActivity.this,
                                    (dialogInterface, i) -> requestPermissions()
                            );
                        } else {
                            Snackbar.make(rootView, getString(R.string.app_permissions), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}