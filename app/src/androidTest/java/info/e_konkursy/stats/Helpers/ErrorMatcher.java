package info.e_konkursy.stats.Helpers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * Created by Adrian Pionka on 2017-03-29.
 */

public class ErrorMatcher {
    public static Matcher<View> withError(final String substring) {
        System.out.print("substring="+substring);
        return withError(is(substring));
    }

    public static Matcher<View> withError(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public boolean matchesSafely(EditText view) {
                final CharSequence error = view.getError();
                System.out.print("error="+error.toString());
                return error != null && stringMatcher.matches(error.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                stringMatcher.describeTo(description);
            }
        };
    }
}
