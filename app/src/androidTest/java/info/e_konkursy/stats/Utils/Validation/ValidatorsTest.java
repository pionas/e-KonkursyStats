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
import info.e_konkursy.stats.R;

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
    public void requiredIsNull() {
        try {
            new RequiredValidation(mContext, null).validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
            assertEquals(mContext.getString(R.string.empty_value), e.getMessage());
        }
    }

    @Test
    public void requiredIsEmpty() {
        try {
            new RequiredValidation(mContext, "").validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
            assertEquals(mContext.getString(R.string.empty_value), e.getMessage());
        }
    }

    @Test
    public void requiredIsNotEmpty() {
        try {
            new RequiredValidation(mContext, "TextView text").validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
    }

    @Test
    public void textIsToShort() {
        try {
            new MinLenghtValidation(mContext, "It is too short", 100).validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
            assertEquals(mContext.getString(R.string.string_to_short), e.getMessage());
        }
    }

    @Test
    public void textNotToShort() {
        try {
            new MinLenghtValidation(mContext, "It is not too short", 5).validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
    }

    @Test
    public void textIsToLong() throws Exception {
        try {
            new MaxLenghtValidation(mContext, "It is too long", 5).validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertEquals(mContext.getString(R.string.string_to_long), e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void textIsNotToLong() throws Exception {
        try {
            new MaxLenghtValidation(mContext, "It is too long", 50).validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
    }

    @Test
    public void isNotMail() throws Exception {
        try {
            new MailValidation(mContext, "TEST_MAIL@dot").validate();
            assertTrue(false);
        } catch (ValidationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isMail() throws Exception {
        try {
            new MailValidation(mContext, "john.doe@gmail.com").validate();
            assertTrue(true);
        } catch (ValidationException e) {
            assertTrue(false);
        }
    }

}