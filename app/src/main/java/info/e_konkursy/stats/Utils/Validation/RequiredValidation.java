package info.e_konkursy.stats.Utils.Validation;

import android.content.Context;

import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class RequiredValidation implements ValidateInterface {
    private Context context;
    private String text;

    public RequiredValidation(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public boolean validate() throws ValidationException {
        if (text == null || text.isEmpty()) {
            throw new ValidationException(context.getString(R.string.empty_value));
        }
        return true;
    }
}
