package info.e_konkursy.stats.Utils.Validation;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import info.e_konkursy.stats.Exception.ValidationException;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class Validators {
    private Context context;
    private TextView textView;

    private ArrayList<ValidateInterface> validateArrayList;

    public Validators(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
        validateArrayList = new ArrayList<>();
    }


    public Validators required() {
        validateArrayList.add(new RequiredValidation(context, textView));
        return this;
    }

    public Validators minLenght(int lenght) {
        validateArrayList.add(new MinLenghtValidation(context, textView, lenght));
        return this;
    }

    public Validators maxLenght(int lenght) {
        validateArrayList.add(new MaxLenghtValidation(context, textView, lenght));
        return this;
    }

    public Validators isMail() {
        validateArrayList.add(new MailValidation(context, textView));
        return this;
    }

    public void validate() throws ValidationException {
        for (ValidateInterface validateInterface : validateArrayList) {
            validateInterface.validate();
        }
    }

    public TextView getTextView() {
        return textView;
    }
}
