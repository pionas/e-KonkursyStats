package info.e_konkursy.stats.Fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import butterknife.BindView;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public abstract class BaseFragment extends Fragment {
    @BindView(R.id.container)
    ViewGroup rootView;

    private AlertDialog dialog;

    public void initDialog() {
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(getActivity());
        builderDialog.setMessage(R.string.dialog_loading)
                .setNegativeButton(R.string.button_cancel, (dialog1, id) -> hideDialog());
        builderDialog.setCancelable(false);
        dialog = builderDialog.create();
    }

    public void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void showDialog() {
        getActivity().runOnUiThread(() -> dialog.show());
    }

    public void hideDialog() {
        getActivity().runOnUiThread(() -> dialog.dismiss());
    }
}
