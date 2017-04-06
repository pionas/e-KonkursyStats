package info.e_konkursy.stats.Interface;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public interface BaseView<T> {
    void updateData(T o);

    void showSnackbar(String msg);

    void showDialog();

    void hideDialog();

    FragmentActivity getActivity();

    String getString(int rId);

    void initDialog();
}
