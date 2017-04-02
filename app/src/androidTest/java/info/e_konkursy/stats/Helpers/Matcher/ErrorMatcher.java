package info.e_konkursy.stats.Helpers.Matcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * ErrorMatcher check View have any error
 * Created by Adrian Pionka on 29 marzec 2017
 * adrian@pionka.com
 */

public class ErrorMatcher {
    public static Matcher<View> withError(final String substring) {
        return withError(is(substring));
    }

    private static Matcher<View> withError(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public boolean matchesSafely(EditText view) {
                final CharSequence error = view.getError();
                return stringMatcher.matches(error.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                stringMatcher.describeTo(description);
            }
        };
    }
}
