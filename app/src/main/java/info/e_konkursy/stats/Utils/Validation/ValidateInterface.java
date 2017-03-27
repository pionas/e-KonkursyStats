package info.e_konkursy.stats.Utils.Validation;

import info.e_konkursy.stats.Exception.ValidationException;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public interface ValidateInterface {
    boolean validate() throws ValidationException;
}
