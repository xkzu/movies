package cl.duoc.app.movies.util;

public class MovieUtil {

    private MovieUtil() {
    }

    public static boolean isEmptyOrNull(String string) {
        return null == string || string.isEmpty();
    }
}
