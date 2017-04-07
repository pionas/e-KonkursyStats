package info.e_konkursy.stats.Fragment;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Helpers.ActionHelper;
import info.e_konkursy.stats.Helpers.Matcher.ErrorMatcher;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.ContactFragmentMVP;
import info.e_konkursy.stats.Mock.ApiServiceMock;
import info.e_konkursy.stats.Model.ContactFragmentModel;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Repository.ContactRepository;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Adrian Pionka on 2017-04-07.
 */
public class ContactFragmentTest {
    private static final String STRING_TO_BE_TYPED = "Espresso";
    private static final String TEST_MAIL = "jonh.doe@gmail.com";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private String errorMail;
    private String textIsToShort;
    private String textIsToLong;
    private String messageWasSend;

    @Before
    public void setUp() throws Exception {
        errorMail = mActivityRule.getActivity().getResources().getString(R.string.wrong_mail);
        textIsToShort = mActivityRule.getActivity().getResources().getString(R.string.string_to_short);
        textIsToLong = mActivityRule.getActivity().getResources().getString(R.string.string_to_long);
        messageWasSend = mActivityRule.getActivity().getResources().getString(R.string.message_was_send);

        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("MAIN_ACTIVITY_LOAD_DEFAULT_FRAGMENT", false);
        mActivityRule.launchActivity(i);
        ContactFragment fragment = ContactFragment.newInstance();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment, fragment.getTag()).commit();

        mActivityRule.getActivity().runOnUiThread(() -> mActivityRule.getActivity().getSupportFragmentManager().executePendingTransactions());
        getInstrumentation().waitForIdleSync();

        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(1)));


        ApiService apiService = new ApiServiceMock();
        ContactRepository repository = new ContactRepository(apiService);
        ContactFragmentMVP.Model model = new ContactFragmentModel(repository);

        Fragment page = mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(fragment.getTag());

        if (page != null) {
            ((ContactFragment) page).getPresenter().setModel(model);
        }
    }

    @Test
    public void formEmptyMailValidateError() {
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextMail)).check(matches(ErrorMatcher.withError(errorMail)));
    }


    @Test
    public void formMailValidate() {
        onView(withId(R.id.editTextName)).perform(typeText(TEST_MAIL), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(withText(TEST_MAIL)));
    }

    @Test
    public void formNameValidateError() {
        onView(withId(R.id.editTextName)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextName)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void formNameTooShortValidateError() {
        onView(withId(R.id.editTextName)).perform(typeText("sa"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formNameTooLongValidateError() {
        onView(withId(R.id.editTextName)).perform(typeText("Sorry man but this name is too long for this input ;)"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToLong)));
    }

    @Test
    public void formMessageValidateError() {
        onView(withId(R.id.editTextMessage)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextMessage)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void formMessageTooShortValidateError() {
        onView(withId(R.id.editTextMessage)).perform(typeText("mes"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextMessage)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formEmptyValidateError() {
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(ErrorMatcher.withError(textIsToShort)));
        onView(withId(R.id.editTextMail)).check(matches(ErrorMatcher.withError(errorMail)));
        onView(withId(R.id.editTextMessage)).check(matches(ErrorMatcher.withError(textIsToShort)));
    }

    @Test
    public void formSendMessageValidate() {
        onView(withId(R.id.editTextName)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.editTextMail)).perform(typeText(TEST_MAIL), closeSoftKeyboard());
        onView(withId(R.id.editTextMessage)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        closeSoftKeyboard();
        onView(withId(R.id.buttonSendForm)).perform(click());
        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(2)));
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(messageWasSend))).check(matches(isDisplayed()));
        onView(withId(R.id.editTextName)).check(matches(withText(STRING_TO_BE_TYPED)));
        onView(withId(R.id.editTextMail)).check(matches(withText(TEST_MAIL)));
        onView(withId(R.id.editTextMessage)).check(matches(withText(STRING_TO_BE_TYPED)));
    }


}