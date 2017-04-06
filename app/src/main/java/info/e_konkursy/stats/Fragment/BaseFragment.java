package info.e_konkursy.stats.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import butterknife.BindView;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.UIMessage;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;

    private AlertDialog dialog;

    public void initDialog() {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(getActivity());
        builderDialog.setMessage(R.string.dialog_loading)
                .setNegativeButton(R.string.button_cancel, (dialog1, id) -> hideDialog());
        builderDialog.setCancelable(false);
        dialog = builderDialog.create();
    }

    public void showSnackbar(String msg) {
        UIMessage.showMessage(rootView, msg);
    }

    public void showDialog() {
        getActivity().runOnUiThread(() -> dialog.show());
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void openUrl(String url) {
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_up_out);
    }
}
