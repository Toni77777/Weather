package by.grodno.toni7777.weather.util;

import java.util.List;

import by.grodno.toni7777.weather.db.mdefault.TemperatureDefault;
import by.grodno.toni7777.weather.db.mdefault.WeatherDataDefault;
import by.grodno.toni7777.weather.db.mdefault.WeatherDayDefault;
import by.grodno.toni7777.weather.db.mdefault.WeatherDefault;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import io.realm.RealmList;

public final class ConverterDefault {

    public static final String DEFAULT_CITY = "DEFAULT_CITY_TAG";

    private ConverterDefault() {
    }

    public static WeatherDataDefault converteDVOtoDefault(WeatherDataDVO weatherDataDVO) {

        return new WeatherDataDefault(DEFAULT_CITY, weatherDataDVO.getCity().getName(),
                convertToRealmListWeatherDayDSO(weatherDataDVO.getCity().getName(),
                        weatherDataDVO.getWeatherDays()));
    }


    private static RealmList<WeatherDayDefault> convertToRealmListWeatherDayDSO(String cityName, List<WeatherDayDVO> list) {

        RealmList<WeatherDayDefault> realmList = new RealmList<>();

        for (WeatherDayDVO sourseDVO : list) {
            WeatherDayDefault weatherDayDefault = new WeatherDayDefault(cityName,
                    sourseDVO.getTime(),
                    new TemperatureDefault(sourseDVO.getTemperature().getDay(), sourseDVO.getTemperature().getNight()),
                    new WeatherDefault(sourseDVO.getWeather().getId(), sourseDVO.getWeather().getMain(), null),
                    sourseDVO.getPressure(),
                    sourseDVO.getHumidity(),
                    sourseDVO.getSpeed());

            realmList.add(weatherDayDefault);
        }

        return realmList;
    }
}
