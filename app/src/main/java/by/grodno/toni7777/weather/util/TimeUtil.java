package by.grodno.toni7777.weather.util;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import by.grodno.toni7777.weather.R;

public final class TimeUtil {

    private TimeUtil() {
    }

    public static String getDateTime(Context context, long utc) {
        return new StringBuilder()
                .append(getDayOfWeek(context, utc))
                .append("\n")
                .append(getMonthWithDay(context, utc))
                .toString();
    }

    public static String getMonthWithDay(Context context, long utc) {
        long time = utc * Constants.UTC_UTIL;
        DateTime dateTimeUtc = new DateTime(time, DateTimeZone.UTC);
        int month = dateTimeUtc.getMonthOfYear();
        return String.valueOf(dateTimeUtc.getDayOfMonth()) + Constants.SEPARATOR + getMonth(context, month);
    }


    private static String getMonth(Context context, int monthNumber) {
        switch (monthNumber) {
            case JANUARY:
                return context.getString(R.string.january);
            case FEBRUARY:
                return context.getString(R.string.february);
            case MARCH:
                return context.getString(R.string.march);
            case APRIL:
                return context.getString(R.string.april);
            case MAY:
                return context.getString(R.string.may);
            case JUNE:
                return context.getString(R.string.june);
            case JULE:
                return context.getString(R.string.jule);
            case AUGUST:
                return context.getString(R.string.august);
            case SEPTEMBER:
                return context.getString(R.string.september);
            case OCTOBER:
                return context.getString(R.string.october);
            case NOVEMBER:
                return context.getString(R.string.november);
            case DECEMBER:
                return context.getString(R.string.december);
            default:
                throw new IllegalArgumentException("Not valid month number =" + monthNumber);
        }

    }

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULE = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    public static String getDayOfWeek(Context context, long utc) {
        long time = utc * Constants.UTC_UTIL;
        DateTime dateTimeUtc = new DateTime(time, DateTimeZone.UTC);
        int day = dateTimeUtc.getDayOfWeek();
        return getDay(context, day);
    }

    private static String getDay(Context context, int dayNumber) {
        switch (dayNumber) {
            case MONDAY:
                return context.getString(R.string.monday);
            case TUESDAY:
                return context.getString(R.string.thursday);
            case WEDNESDAY:
                return context.getString(R.string.wednesday);
            case THURSDAY:
                return context.getString(R.string.thursday);
            case FRIDAY:
                return context.getString(R.string.friday);
            case SATURDAY:
                return context.getString(R.string.saturday);
            case SUNDAY:
                return context.getString(R.string.sunday);
            default:
                throw new IllegalArgumentException("Not valid day number =" + dayNumber);
        }

    }

    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 3;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;

    public static boolean validDate(long utc) {
        long sourse = utc * Constants.UTC_UTIL;
        DateTime dateTime = new DateTime();
        int minDay = dateTime.getMinuteOfDay() - Constants.MINUTES_RESERVE;
        long timeNowDay = dateTime.getMillis() - minDay * Constants.SECOND_IN_MINUTES;
        return sourse > timeNowDay;
    }
}
