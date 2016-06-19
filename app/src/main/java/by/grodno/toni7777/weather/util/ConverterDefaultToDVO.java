package by.grodno.toni7777.weather.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.db.mdefault.WeatherDataDefault;
import by.grodno.toni7777.weather.db.mdefault.WeatherDayDefault;
import by.grodno.toni7777.weather.ui.model.CityDVO;
import by.grodno.toni7777.weather.ui.model.TemperatureDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import io.realm.RealmList;

public class ConverterDefaultToDVO {

    public static WeatherDataDVO converteDSOtoDVO(WeatherDataDefault weatherDataDefault) {

        return new WeatherDataDVO(new CityDVO(weatherDataDefault.getCity(), null),
                null,
                null,
                convertToListWeatherDayDTO(weatherDataDefault.getCity(), weatherDataDefault.getWeatherDayDefault()));
    }

    private static List<WeatherDayDVO> convertToListWeatherDayDTO(String cityName, RealmList<WeatherDayDefault> realm) {
        List<WeatherDayDVO> result = new ArrayList<>();

        for (WeatherDayDefault sourseDSO : realm) {
            WeatherDayDVO weatherDayDVO = new WeatherDayDVO(cityName,
                    sourseDSO.getTime(),
                    new TemperatureDVO(sourseDSO.getTemperatureDefault().getDay(), sourseDSO.getTemperatureDefault().getNight()),
                    new WeatherDVO(sourseDSO.getWeatherDefault().getId(),
                            sourseDSO.getWeatherDefault().getMain(),
                            sourseDSO.getWeatherDefault().getDescription()),
                    sourseDSO.getPressure(),
                    sourseDSO.getHumidity(),
                    sourseDSO.getSpeed());

            result.add(weatherDayDVO);
        }
        return result;
    }
}
