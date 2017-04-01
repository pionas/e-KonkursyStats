package info.e_konkursy.stats.Activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import info.e_konkursy.stats.Helpers.ActionHelper;
import info.e_konkursy.stats.Helpers.Matcher.ErrorMatcher;
import info.e_konkursy.stats.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by Adrian Pionka on 2017-03-29.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    public static final String STRING_TO_BE_TYPED = "Espresso";
    public static final String TEST_MAIL = "jonh.doe@gmail.com";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private String errorMail;
    private String textIsToShort;
    private String textIsToLong;


    @Before
    public void setUp() throws Exception {
        errorMail = mActivityRule.getActivity().getResources().getString(R.string.wrong_mail);
        textIsToShort = mActivityRule.getActivity().getResources().getString(R.string.string_to_short);
        textIsToLong = mActivityRule.getActivity().getResources().getString(R.string.string_to_long);
    }

    @Test
    public void formEmptyMailValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextMail)).check(matches(ErrorMatcher.withError(errorMail)));
    }

    @Test
    public void formMailValidate() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextName)).perform(typeText(TEST_MAIL), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(withText(TEST_MAIL)));
    }

    @Test
    public void formNameValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextName)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextName)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void formNameTooShortValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextName)).perform(typeText("sa"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formNameTooLongValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextName)).perform(typeText("Sorry man but this name is too long for this input ;)"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToLong)));
    }

    @Test
    public void formMessageValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextMessage)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextMessage)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void formMessageTooShortValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextMessage)).perform(typeText("mes"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextMessage)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formEmptyValidateError() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToShort)));
        onView(withId(R.id.editTextMail)).check(matches(ErrorMatcher.withError(errorMail)));
        onView(withId(R.id.editTextMessage)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formSendMessageValidate() {
        onView(withId(R.id.navigation_contact)).perform(click());
        onView(withId(R.id.editTextName)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextMail)).perform(typeText(TEST_MAIL), closeSoftKeyboard());
        onView(withId(R.id.editTextMessage)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(5)));
        onView(withId(R.id.editTextName)).check(matches(withText(STRING_TO_BE_TYPED)));
        onView(withId(R.id.editTextMail)).check(matches(withText(TEST_MAIL)));
        onView(withId(R.id.editTextMessage)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

}