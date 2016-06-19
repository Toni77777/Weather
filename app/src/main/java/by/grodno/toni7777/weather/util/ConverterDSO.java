package by.grodno.toni7777.weather.util;

import java.util.List;

import by.grodno.toni7777.weather.db.model.TemperatureDSO;
import by.grodno.toni7777.weather.db.model.WeatherDSO;
import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import by.grodno.toni7777.weather.db.model.WeatherDayDSO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import io.realm.RealmList;

public final class ConverterDSO {

    private ConverterDSO() {
    }

    public static WeatherDataDSO converteDVOtoDSO(WeatherDataDVO weatherDataDVO) {

        return new WeatherDataDSO(weatherDataDVO.getCity().getName(),
                convertToRealmListWeatherDayDSO(weatherDataDVO.getCity().getName(),
                        weatherDataDVO.getWeatherDays()));
    }

    private static RealmList<WeatherDayDSO> convertToRealmListWeatherDayDSO(String cityName, List<WeatherDayDVO> list) {

        RealmList<WeatherDayDSO> realmList = new RealmList<>();

        for (WeatherDayDVO sourseDVO : list) {
            WeatherDayDSO weatherDayDSO = new WeatherDayDSO(cityName,
                    sourseDVO.getTime(),
                    new TemperatureDSO(sourseDVO.getTemperature().getDay(), sourseDVO.getTemperature().getNight()),
                    new WeatherDSO(sourseDVO.getWeather().getId(),
                            sourseDVO.getWeather().getMain(),
                            sourseDVO.getWeather().getDescription()),
                    sourseDVO.getPressure(),
                    sourseDVO.getHumidity(),
                    sourseDVO.getSpeed());

            realmList.add(weatherDayDSO);
        }

        return realmList;
    }
}
