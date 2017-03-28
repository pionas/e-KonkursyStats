package info.e_konkursy.stats.Validators;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.Helpers.KeyboardHelper;
import info.e_konkursy.stats.Interface.MainActivityMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Validation.Validators;

/**
 * Created by Adrian Pionka on 2017-03-28.
 */

public class ContactValidator {
    private Activity activity;
    private MainActivityMVP.Presenter presenter;

    EditText editTextName;
    EditText editTextMail;
    EditText editTextMessage;
    private ArrayList<Validators> arrayList;

    public ContactValidator(MainActivity activity, MainActivityMVP.Presenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
        this.presenter.setView(activity);

        initEditText();
        initValidate();
    }
    private void initEditText() {
        editTextName = (EditText) activity.findViewById(R.id.editTextName);
        editTextMail = (EditText) activity.findViewById(R.id.editTextMail);
        editTextMessage = (EditText) activity.findViewById(R.id.editTextMessage);
    }

    private void initValidate() {
        arrayList = new ArrayList<>();
        arrayList.add(new Validators(activity, editTextName).minLenght(5).maxLenght(50).required());
        arrayList.add(new Validators(activity, editTextMail).isMail().required());
        arrayList.add(new Validators(activity, editTextMessage).minLenght(5).required());

    }

    public void validate() {
        boolean validate = true;
        for (Validators v : arrayList) {
            v.getTextView().setError(null);
            try {
                v.validate();
            } catch (ValidationException e) {
                v.getTextView().setError(e.getMessage());
                validate = false;
                if (activity.getCurrentFocus() == null) {
                    v.getTextView().requestFocus();
                }
            }
        }

        if (validate) {
            if (presenter != null) {
                ContactMessage contactMessage = new ContactMessage();
                contactMessage.setName(editTextName.getText().toString());
                contactMessage.setMail(editTextMail.getText().toString());
                contactMessage.setContent(editTextMessage.getText().toString());
                presenter.sendMessage(contactMessage);
            }
            KeyboardHelper.KeyboardHide(activity, activity.getCurrentFocus());
        }
    }
}
