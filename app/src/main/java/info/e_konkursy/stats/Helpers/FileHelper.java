package info.e_konkursy.stats.Helpers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Adrian Pionka on 28 marzec 2017
 * adrian@pionka.com
 */
public class FileHelper {
    public static void downloadFile(String fileURL, File file) throws Exception {
        if (fileURL == null || fileURL.isEmpty() || file == null) {
            throw new Exception("fileURL and file can not be empty");
        }
        int count;

        try {
            file.createNewFile();
            URL urlConnect = new URL(fileURL);
            URLConnection conection = urlConnect.openConnection();
            conection.connect();
            InputStream input = new BufferedInputStream(urlConnect.openStream(), 8192);
            OutputStream output = new FileOutputStream(file);
            byte data[] = new byte[1024];
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();

        } catch (Exception e) {
            if (file.exists()) {
                file.delete();
            }
            throw new Exception(e);
        }
    }
}
