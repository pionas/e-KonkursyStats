package info.e_konkursy.stats.Utils.Validation;


import android.content.Context;
import android.widget.TextView;

import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class MailValidation implements ValidateInterface {
    private Context context;
    private TextView textView;

    public MailValidation(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public boolean validate() throws ValidationException {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textView.getText().toString()).matches()) {
            throw new ValidationException(context.getString(R.string.wrong_mail));
        }
        return true;
    }
}
