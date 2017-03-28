package info.e_konkursy.stats.Helpers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Adrian Pionka on 2017-03-28.
 */

public class DateHelper {
    public static String changeFormatDate(String format, String date) {
        Timestamp timestamp = Timestamp.valueOf(date);
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);//dd/MM/yyyy
        return sdfDate.format(timestamp);
    }
}
