package info.e_konkursy.stats.Helpers;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class StringsHelper {
    public static String stripslashes(String string) {
        return string.replace("\\", "");
    }
}
