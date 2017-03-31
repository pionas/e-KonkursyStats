package info.e_konkursy.stats.Helpers.ViewAction;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.hamcrest.Matcher;

import info.e_konkursy.stats.Interface.ListAdapterInterface;

/**
 * Created by Adrian Pionka on 2017-03-31.
 */

public class ClearAdapter implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isDisplayed();
    }

    @Override
    public String getDescription() {
        return "Clear adapter.";
    }

    @Override
    public void perform(UiController uiController, View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getAdapter() instanceof ListAdapterInterface) {
            for (int i = recyclerView.getAdapter().getItemCount() - 1; i >= 0; i--) {
                ((ListAdapterInterface) recyclerView.getAdapter()).removeAt(i);
            }
        }

    }
}
