package info.e_konkursy.stats.Utils.Validation;

import android.content.Context;
import android.widget.TextView;

import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.R;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class MinLenghtValidation implements ValidateInterface {
    private Context context;
    private String text;
    private int minLenght;

    public MinLenghtValidation(Context context, String text, int minLenght) {
        this.context = context;
        this.text = text;
        this.minLenght = minLenght;
    }

    @Override
    public boolean validate() throws ValidationException {
        System.out.print("MinLenghtValidation");
        if (text == null || text.isEmpty() || text.length() < minLenght) {
            throw new ValidationException(context.getString(R.string.string_to_short));
        }
        return true;
    }
}
