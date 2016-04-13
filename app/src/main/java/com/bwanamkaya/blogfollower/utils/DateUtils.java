package com.bwanamkaya.blogfollower.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by bwana.mkaya on 17/01/16.
 */
public class DateUtils {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
    private static Date dateReturn;
    private static String stringDate;

    public static Date StringToDate(String date) throws ParseException {
        if (date != null) {
            dateReturn = (Date) SIMPLE_DATE_FORMAT.parse(date);
        }
        return dateReturn;
    }

    public static String DateToString(Date date) {
        if (date != null) {
            stringDate = SIMPLE_DATE_FORMAT.toPattern();
        }
        return stringDate;
    }
}
