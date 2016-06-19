package by.grodno.toni7777.weather.util;

public final class Constants {

    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String FORECAST_DAILY_URL = "/data/2.5/forecast/daily";

    public static final String QUERY_CITY = "q";
    public static final String QUERY_LANGUAGE = "lang";
    public static final String QUERY_DAY_COUNT = "cnt";
    public static final String QUERY_TEMP_UNITS = "units";
    public static final String QUERY_APIKEY = "APPID";

    public static final String APIKEY_VALUE = "7246f8da7f6f0d62458c3eb424a4ba8d";

    public static final String COUNT_WEATHER_DAYS = "14";
    public static final String TEMPERATURE_UNITS = "metric";

    public static final String EMPTY = "";

    public static final String UNIT_CELSIUS = "Celsius";
    public static final String UNIT_FAHRENHEIT = "Fahrenheit";

    public static final int NOT_FOUND = -1;

    public static final String SHARE_CITY_KEY = "cityNameShare";
    public static final String SHARE_TIME_KEY = "timeShare";

    public static final long DEFAULT_TIME = 0;

    public static final String SEPARATOR = " ";

    public static final String DEFAULT_DAYS = "14";
    public static final String DEFAULT_UNIT = "Celsius";

    public static final String INTETEST = "%";

    public static final long UTC_UTIL = 1000L;

    public static final int REQUEST_PERMISSION = 10;

    public static final int MINUTES_RESERVE = 1;
    public static final int SECOND_IN_MINUTES = 60;

    private Constants() {
    }

}
