package ventasds3.Utils;

public class Util {

    public static boolean isLong(String num) {
        try {
            Long.valueOf(num);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDouble(String num) {
        try {
            Double.valueOf(num);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
