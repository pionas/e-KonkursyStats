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
    private TextView textView;
    private int minLenght;

    public MinLenghtValidation(Context context, TextView textView, int minLenght) {
        this.context = context;
        this.textView = textView;
        this.minLenght = minLenght;
    }

    @Override
    public boolean validate() throws ValidationException {
        String text = textView.getText().toString();
        if (text.isEmpty() || text.length() < minLenght) {
            throw new ValidationException(context.getString(R.string.string_to_short));
        }
        return true;
    }
}
