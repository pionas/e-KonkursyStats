package info.e_konkursy.stats.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class BaseActivity extends AppCompatActivity {
    protected FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragManager = getSupportFragmentManager();
    }

    @Override
    public void onBackPressed() {
        int howMany = fragManager.getBackStackEntryCount();
        if (howMany == 1) {
            finish();
        } else {
            try {
                fragManager.popBackStackImmediate();
            } catch (Exception e) {

            }
            Fragment frag;
            howMany = fragManager.getBackStackEntryCount();
            frag = fragManager.getFragments().get(howMany > 0 ? howMany - 1 : howMany);
            if (frag == null) {
                String fragmentTag = fragManager.getBackStackEntryAt(fragManager.getBackStackEntryCount() - 1).getName();
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                frag = currentFragment;
            }

            if (frag != null) {
                frag.onResume();
            }

        }
    }
}
