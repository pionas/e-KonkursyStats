package info.e_konkursy.stats.Utils.Validation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.e_konkursy.stats.Activity.MainActivity;
import info.e_konkursy.stats.Exception.ValidationException;

import static org.junit.Assert.*;

/**
 * Created by Adrian Pionka on 2017-03-28.
 */
@RunWith(AndroidJUnit4.class)
public class ValidatorsTest {
    private Context mContext;
    private TextView mTextView;
    private Validators mValidators;


    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getTargetContext();
        mTextView = new TextView(mContext);

        mValidators = new Validators(mContext, mTextView);
    }

    @Test
    public void textViewRequiredIsEmpty() {

        try {
            mValidators.required().validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void textViewRequiredIsNotEmpty() {
        mTextView.setText("TextView text");
        try {
            mValidators.required().validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
        mTextView.setText(null);
    }

    @Test
    public void textViewIsToShort() {
        mTextView.setText("It is to short");
        try {
            mValidators.minLenght(20).validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
        }
        mTextView.setText(null);
    }

    @Test
    public void textViewIsNotToShort() {
        mTextView.setText("It is not to short");
        try {
            mValidators.minLenght(10).validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
        mTextView.setText(null);
    }

    @Test
    public void textViewIsToLong() throws Exception {
        mTextView.setText("It is not to long");
        try {
            mValidators.maxLenght(10).validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
        }
        mTextView.setText(null);
    }

    @Test
    public void textViewIsNotToLong() throws Exception {
        mTextView.setText("It is not to long");
        try {
            mValidators.maxLenght(100).validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
        mTextView.setText(null);
    }

    @Test
    public void isNotMail() throws Exception {
        mTextView.setText("mail@dot");
        try {
            mValidators.isMail().validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
        }
        mTextView.setText(null);
    }

    @Test
    public void isMail() throws Exception {
        mTextView.setText("john.doe@gmail.com");
        try {
            mValidators.isMail().validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
        mTextView.setText(null);
    }

}