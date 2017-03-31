package info.e_konkursy.stats.Presenter;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Helpers.ActionHelper;
import info.e_konkursy.stats.Helpers.ApiServiceMock;
import info.e_konkursy.stats.Helpers.ViewAction.ClearAdapter;
import info.e_konkursy.stats.Helpers.ViewAssertion.RecyclerViewItemCountAssertion;
import info.e_konkursy.stats.Interface.ApiService;
import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.MainActivityModel;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Repository.StatsRepository;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by Adrian Pionka on 2017-03-31.
 */
public class MainActivityPresenterTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivityPresenter mainActivityPresenter;

    @Before
    public void setUp() throws Exception {
        ApiService apiService = new ApiServiceMock();
        StatsRepository statsRepository = new StatsRepository(apiService);
        MainActivityMVP.Model model = new MainActivityModel(statsRepository);
        mainActivityPresenter = new MainActivityPresenter(model);
        mainActivityPresenter.setView(mActivityRule.getActivity());
    }

    @Test
    public void articleDataEmpty() {
        onView(withText(R.id.listViewTopArticle)).check(doesNotExist());
    }

    @Test
    public void loadArticleData() throws Exception {
        int totalElements = 15;
        mainActivityPresenter.loadArticleData();
        onView(withId(R.id.listViewTopArticle)).check(new RecyclerViewItemCountAssertion(totalElements));
    }

    @Test
    public void userDataEmpty() {
        onView(withText(R.id.listViewTopPeople)).check(doesNotExist());
    }

    @Test
    public void loadUserData() throws Exception {
        onView(withId(R.id.navigation_users)).perform(click());
        onView(isRoot()).perform(ActionHelper.waitFor(TimeUnit.SECONDS.toMillis(5)));
        onView(withId(R.id.listViewTopPeople)).perform(new ClearAdapter());
        int totalElements = 0;
        onView(withId(R.id.listViewTopPeople)).check(new RecyclerViewItemCountAssertion(totalElements));
    }

}