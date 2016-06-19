package by.grodno.toni7777.weather.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.network.model.CityDTO;
import by.grodno.toni7777.weather.network.model.WeatherDataDTO;
import by.grodno.toni7777.weather.network.model.WeatherDayDTO;
import by.grodno.toni7777.weather.ui.model.CityDVO;
import by.grodno.toni7777.weather.ui.model.TemperatureDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;

public final class ConverterDVO {

    private ConverterDVO() {
    }

    public static WeatherDataDVO converteDTOtoDVO(WeatherDataDTO weatherDataDTO) {

        return new WeatherDataDVO(convertToCityDVO(weatherDataDTO.getCity()),
                weatherDataDTO.getCode(),
                weatherDataDTO.getMessage(),
                convertToListWeatherDayDTO(weatherDataDTO.getCity().getName(), weatherDataDTO.getWeatherDays()));
    }

    private static CityDVO convertToCityDVO(CityDTO cityDTO) {
        if (cityDTO != null) {
            return new CityDVO(cityDTO.getName(), cityDTO.getCountry());
        } else {
            return null;
        }
    }

    private static List<WeatherDayDVO> convertToListWeatherDayDTO(String cityName, List<WeatherDayDTO> list) {
        if (list != null) {
            List<WeatherDayDVO> resultDVO = new ArrayList<>(list.size());
            for (WeatherDayDTO sourseDTO : list) {
                WeatherDayDVO dayDVO = new WeatherDayDVO(cityName,
                        sourseDTO.getTime(),
                        new TemperatureDVO(sourseDTO.getTemperature().getDay(), sourseDTO.getTemperature().getNight()),
                        new WeatherDVO(sourseDTO.getWeather().get(0).getId(), sourseDTO.getWeather().get(0).getMain(), sourseDTO.getWeather().get(0).getDescription()),
                        sourseDTO.getPressure(),
                        sourseDTO.getHumidity(),
                        sourseDTO.getSpeed());
                resultDVO.add(dayDVO);
            }
            return resultDVO;
        } else {
            return null;
        }
    }
}
