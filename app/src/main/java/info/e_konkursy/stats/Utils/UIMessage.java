package info.e_konkursy.stats.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Adrian Pionka on 2017-04-03.
 */

public class UIMessage {
    public static void showMessage(View parent, String message) {
        if (Environment.useSnackbar) {
            Snackbar.make(parent, message, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
