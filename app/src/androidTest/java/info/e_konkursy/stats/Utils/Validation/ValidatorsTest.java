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

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getTargetContext();
    }

    @Test(expected = ValidationException.class)
    public void requiredIsNull() throws ValidationException {
        new RequiredValidation(mContext, null).validate();
    }

    @Test(expected = ValidationException.class)
    public void requiredIsEmpty() throws ValidationException {
        new RequiredValidation(mContext, "").validate();
    }

    @Test
    public void requiredIsNotEmpty() throws ValidationException {
        new RequiredValidation(mContext, "TextView text").validate();
        assertTrue(true);
    }

    @Test(expected = ValidationException.class)
    public void textIsToShort() throws ValidationException {
        new MinLenghtValidation(mContext, "It is too short", 100).validate();
    }

    @Test
    public void textNotToShort() throws ValidationException {
        new MinLenghtValidation(mContext, "It is not too short", 5).validate();
        assertTrue(true);
    }

    @Test(expected = ValidationException.class)
    public void textIsToLong() throws ValidationException {
        new MaxLenghtValidation(mContext, "It is too long", 5).validate();
    }

    @Test
    public void textIsNotToLong() throws ValidationException {
        new MaxLenghtValidation(mContext, "It is too long", 50).validate();
        assertTrue(true);
    }

    @Test(expected = ValidationException.class)
    public void isNotMail() throws ValidationException {
        new MailValidation(mContext, "TEST_MAIL@dot").validate();
    }

    @Test
    public void isMail() throws ValidationException {
        new MailValidation(mContext, "john.doe@gmail.com").validate();
        assertTrue(true);
    }

}