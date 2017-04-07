package info.e_konkursy.stats.Fragment;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Helpers.ActionHelper;
import info.e_konkursy.stats.Helpers.Matcher.RecyclerViewMatcher;
import info.e_konkursy.stats.Helpers.ViewAction.ClearAdapter;
import info.e_konkursy.stats.Helpers.ViewAssertion.RecyclerViewItemCountAssertion;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.TopPeopleFragmentMVP;
import info.e_konkursy.stats.Mock.ApiServiceMock;
import info.e_konkursy.stats.Model.TopPeopleFragmentModel;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Repository.TopPeopleFragmentRepository;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Adrian Pionka on 2017-04-07.
 */
public class TopPeopleFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private TopPeopleFragment topPeopleFragment;

    @Before
    public void setUp() throws Exception {
        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("MAIN_ACTIVITY_LOAD_DEFAULT_FRAGMENT", false);
        mActivityRule.launchActivity(i);
        TopPeopleFragment fragment = TopPeopleFragment.newInstance(false);
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment, fragment.getClass().getSimpleName()).commit();

        mActivityRule.getActivity().runOnUiThread(() -> mActivityRule.getActivity().getSupportFragmentManager().executePendingTransactions());

        getInstrumentation().waitForIdleSync();

        ApiService apiService = new ApiServiceMock();
        TopPeopleFragmentRepository repository = new TopPeopleFragmentRepository(apiService);
        TopPeopleFragmentMVP.Model model = new TopPeopleFragmentModel(repository);
        topPeopleFragment = (TopPeopleFragment) mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        topPeopleFragment.getPresenter().setModel(model);
    }

    @Test
    public void userDataEmpty() {
        onView(withText(R.id.list)).check(doesNotExist());
    }

    @Test
    public void EmptyUserData() throws Exception {
        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(2)));
        onView(withId(R.id.list)).perform(new ClearAdapter());
        int totalElements = 0;
        onView(withId(R.id.list)).check(new RecyclerViewItemCountAssertion(totalElements));
    }

    @Test
    public void loadUserData() throws Exception {
        int totalElements = 15;
        topPeopleFragment.getPresenter().loadData();
        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(1)));

        onView(withId(R.id.list)).check(new RecyclerViewItemCountAssertion(totalElements));

        onView(new RecyclerViewMatcher(R.id.list)
                .atPositionOnView(9, R.id.textViewUsername))
                .check(matches(withText("mixik")));
    }
}