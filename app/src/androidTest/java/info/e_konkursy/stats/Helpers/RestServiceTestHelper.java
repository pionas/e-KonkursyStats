package info.e_konkursy.stats.Helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * @author rebeccafranks
 * @since 15/10/24.
 */
public class RestServiceTestHelper {

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) {

        try {
            final InputStream stream = getInstrumentation().getContext().getResources().getAssets().open(filePath);
            String ret = convertStreamToString(stream);
            stream.close();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


}