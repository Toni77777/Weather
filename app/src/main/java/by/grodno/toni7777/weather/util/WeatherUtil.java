package by.grodno.toni7777.weather.util;


import by.grodno.toni7777.weather.R;

import static by.grodno.toni7777.weather.util.Constants.NOT_FOUND;

public final class WeatherUtil {

    private WeatherUtil() {
    }

    public static int getResWeatherImageId(int weatherId) {

        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_thunderstorm7;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_shower_rain5;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain6;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow8;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain6;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow8;   //right
        } else if (weatherId >= 701 && weatherId <= 781) {
            return R.drawable.ic_mistn9;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear_sky1;
        } else if (weatherId == 801) {
            return R.drawable.ic_few_clouds2;
        } else if (weatherId == 802) {
            return R.drawable.ic_scattered_clouds3;
        } else if (weatherId >= 803 && weatherId <= 804) {
            return R.drawable.ic_broken_clouds4;
        }

        return NOT_FOUND;
    }
}
