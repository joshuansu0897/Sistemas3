package proyecto.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static LocalDate getLocalDateFromDate(Date d) {
        if (d == null) {
            return null;
        }
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean isFloat(String numStr) {
        try {
            float numF = Float.valueOf(numStr);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static Date getDateFromLocalDate(LocalDate l) {
        if (l == null) {
            return null;
        }
        return Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //Para mas ejemplos o mas formatos 
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    public static String LocalDateToString(LocalDate l) {
        if (l == null) {
            return null;
        }
        return l.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String LocalDateToStringSpanish(LocalDate l) {
        if (l == null) {
            return null;
        }
        Locale locale = new Locale("es", "ES");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'del' uuuu", locale);
        return l.format(formatter);
    }

    public static String CapitalizeFirstLetter(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
