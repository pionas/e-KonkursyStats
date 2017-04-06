package info.e_konkursy.stats.Utils;

/**
 * Created by Adrian Pionka on 2017-04-05.
 */

public class Environment {
    public static long CACHE_MAX_LIFETIME_IN_MILLIS = 5 * 1000;

    // Default values (PRODUCTION)
    public static boolean showLog = false;
    public static boolean useSnackbar = true;
    public static String baseUrl = "https://www.e-konkursy.info/";
    public static String apiUrl = baseUrl + "api/";
    public static String baseArticleImageUrl = baseUrl + "files/articles/org/";
    public static String localStorage = "/e-konkursy/";
    public static String articleUrl = baseUrl + "konkurs/";

    private static Type type = Type.DEVELOP;


    public static void configure() {
        switch (type) {
            case DEVELOP:
                showLog = true;
                useSnackbar = true;
                break;
            case PREPRODUCTION:
                showLog = true;
                useSnackbar = true;
                break;
            case PRODUCTION:
                showLog = false;
                useSnackbar = true;
                break;
        }
    }

    public enum Type {
        DEVELOP,
        PREPRODUCTION,
        PRODUCTION
    }
}
