package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
    public static Date parseDateFromString(String date) throws ParseException {
        Date dateFromString = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return dateFromString;
    }
}
