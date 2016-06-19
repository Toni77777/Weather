package by.grodno.toni7777.weather.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import by.grodno.toni7777.weather.db.model.WeatherDayDSO;
import by.grodno.toni7777.weather.ui.model.CityDVO;
import by.grodno.toni7777.weather.ui.model.TemperatureDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import io.realm.RealmList;

public final class ConverterDSOtoDVO {

    private ConverterDSOtoDVO() {
    }

    public static WeatherDataDVO converteDSOtoDVO(WeatherDataDSO weatherDataDTO) {

        return new WeatherDataDVO(new CityDVO(weatherDataDTO.getCity(), null),
                null,
                null,
                convertToListWeatherDayDTO(weatherDataDTO.getCity(), weatherDataDTO.getWeatherDayDSO()));
    }

    private static List<WeatherDayDVO> convertToListWeatherDayDTO(String cityName, RealmList<WeatherDayDSO> realm) {
        List<WeatherDayDVO> result = new ArrayList<>();

        for (WeatherDayDSO sourseDSO : realm) {
            WeatherDayDVO weatherDayDVO = new WeatherDayDVO(cityName,
                    sourseDSO.getTime(),
                    new TemperatureDVO(sourseDSO.getTemperatureDSO().getDay(), sourseDSO.getTemperatureDSO().getNight()),
                    new WeatherDVO(sourseDSO.getWeatherDSO().getId(),
                            sourseDSO.getWeatherDSO().getMain(),
                            sourseDSO.getWeatherDSO().getDescription()),
                    sourseDSO.getPressure(),
                    sourseDSO.getHumidity(),
                    sourseDSO.getSpeed());

            result.add(weatherDayDVO);
        }
        return result;
    }
}
