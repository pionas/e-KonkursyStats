package info.e_konkursy.stats.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.e_konkursy.stats.App.App;
import info.e_konkursy.stats.Exception.ValidationException;
import info.e_konkursy.stats.Helpers.KeyboardHelper;
import info.e_konkursy.stats.Interface.ContactFragmentMVP;
import info.e_konkursy.stats.Model.POJO.ContactMessage;
import info.e_konkursy.stats.Module.ContactModule;
import info.e_konkursy.stats.Module.TopPeopleModule;
import info.e_konkursy.stats.R;
import info.e_konkursy.stats.Utils.Validation.Validators;

/**
 * Created by Adrian Pionka on 05 kwiecień 2017
 * adrian@pionka.com
 */
public class ContactFragment extends BaseFragment implements ContactFragmentMVP.View {
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextMail)
    EditText editTextMail;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;

    @Inject
    ContactFragmentMVP.Presenter presenter;

    private FragmentActivity mActivity;


    @SuppressWarnings("unused")
    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

        new App().getContactComponent().contactModule(new ContactModule()).build().inject(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    @OnClick(R.id.buttonSendForm)
    public void initValidate(View view) {
        ArrayList<Validators> arrayList = new ArrayList<>();
        arrayList.add(new Validators(mActivity, editTextName).minLenght(5).maxLenght(50).required());
        arrayList.add(new Validators(mActivity, editTextMail).isMail().required());
        arrayList.add(new Validators(mActivity, editTextMessage).minLenght(5).required());
        boolean validate = true;
        for (Validators v : arrayList) {
            v.getTextView().setError(null);
            try {
                v.validate();
            } catch (ValidationException e) {
                v.getTextView().setError(e.getMessage());
                validate = false;
                if (mActivity.getCurrentFocus() == null) {
                    v.getTextView().requestFocus();
                }
            }
        }

        if (validate) {
            ContactMessage contactMessage = new ContactMessage();
            contactMessage.setName(editTextName.getText().toString());
            contactMessage.setMail(editTextMail.getText().toString());
            contactMessage.setContent(editTextMessage.getText().toString());
            presenter.sendMessage(contactMessage);
            KeyboardHelper.KeyboardHide(mActivity, mActivity.getCurrentFocus());
        }
    }

    private View findViewById(int fId) {
        return mActivity.findViewById(fId);
    }

    @Override
    public void updateData(ContactMessage o) {

    }
}
