package info.e_konkursy.stats.Utils.Validation;

import android.content.Context;
import android.widget.TextView;

import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class MaxLenghtValidation implements ValidateInterface {
    private Context context;
    private String text;
    private int maxLenght;

    public MaxLenghtValidation(Context context, String text, int maxLenght) {
        this.context = context;
        this.text = text;
        this.maxLenght = maxLenght;
    }

    @Override
    public boolean validate() throws ValidationException {
        if (text != null && text.length() > maxLenght) {
            throw new ValidationException(context.getString(R.string.string_to_long));
        }
        return true;
    }
}
