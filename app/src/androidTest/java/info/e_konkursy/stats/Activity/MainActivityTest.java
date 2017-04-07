package info.e_konkursy.stats.Activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import info.e_konkursy.stats.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;


/**
 * MainActivityTest is a test for MainActivity
 * Created by Adrian Pionka on 29 marzec 2017
 * adrian@pionka.com
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getActivityIsNotEmpty() {
        assertTrue(mActivityRule.getActivity() != null);
    }

    @Test
    public void navigationMenuExist() {
        onView(withId(R.id.navigation)).check(matches(isDisplayed()));
    }
}