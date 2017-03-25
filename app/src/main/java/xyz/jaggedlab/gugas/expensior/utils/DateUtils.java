package xyz.jaggedlab.gugas.expensior.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Asus on 24/03/2017.
 */

public class DateUtils {
    private DateUtils instance = new DateUtils();

    private Calendar calendar;

    public DateUtils getInstance() {
        return this.instance;
    }

    private DateUtils() { }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
        return formatter.format(date);
    }

    public static String formatDateWithCustomFormatting(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static Date getDateFromLong(long dateInMillis) {
        return new Date(dateInMillis);
    }

    public static long getLongFromDate(Date date) {
        return date.getTime();
    }

    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static long getBegginingOfDay(Date date) {
        Calendar calendarTemp = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendarTemp.setTime(date);

        calendar.set(calendarTemp.get(Calendar.YEAR), calendarTemp.get(Calendar.MONTH), calendarTemp.get(Calendar.DAY_OF_MONTH));

        return calendar.getTimeInMillis();
    }

    public static boolean compareDates(Date a, Date b) {
        Calendar calendarA = Calendar.getInstance();
        Calendar calendarB = Calendar.getInstance();

        calendarA.setTime(a);
        calendarB.setTime(b);

        return calendarA.get(Calendar.YEAR) == calendarB.get(Calendar.YEAR)
                && calendarA.get(Calendar.MONTH) == calendarB.get(Calendar.MONTH)
                && calendarA.get(Calendar.DAY_OF_MONTH) == calendarB.get(Calendar.DAY_OF_MONTH);
    }
}
