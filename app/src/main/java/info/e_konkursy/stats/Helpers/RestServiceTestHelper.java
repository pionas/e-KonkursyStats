package info.e_konkursy.stats.Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import info.e_konkursy.stats.App.App;

/**
 * @author rebeccafranks
 * @since 15/10/24.
 */
public class RestServiceTestHelper {

    public static String convertStreamToString(InputStream is) throws Exception {
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
            final InputStream stream = new App().getBaseContext().getResources().getAssets().open(filePath);
            String ret = convertStreamToString(stream);
            stream.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}