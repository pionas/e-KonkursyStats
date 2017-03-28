package info.e_konkursy.stats.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 28 marzec 2017
 * adrian@pionka.com
 */
public class DialogManager {

    public static void showPermissionsDialog(Context context, DialogInterface.OnClickListener listenerPositive) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(context.getString(R.string.permission_title));
        builder.setMessage(context.getString(R.string.permission_denied));
        builder.setPositiveButton(context.getString(android.R.string.ok), listenerPositive);
        AlertDialog ad = builder.create();
        ad.show();
    }
}
