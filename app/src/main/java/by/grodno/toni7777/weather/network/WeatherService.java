package by.grodno.toni7777.weather.network;

import by.grodno.toni7777.weather.network.model.WeatherDataDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static by.grodno.toni7777.weather.util.Constants.*;

public interface WeatherService {

    @GET(FORECAST_DAILY_URL)
    Observable<WeatherDataDTO> getRxWeatherDays(@Query(QUERY_CITY) String query,
                                                @Query(QUERY_LANGUAGE) String lang,
                                                @Query(QUERY_DAY_COUNT) String count,
                                                @Query(QUERY_TEMP_UNITS) String format,
                                                @Query(QUERY_APIKEY) String key);

    //http://api.openweathermap.org/data/2.5/forecast/daily?lat=53.6423475&lon=23.864781&cnt=14&appid=7246f8da7f6f0d62458c3eb424a4ba8d

    @GET(FORECAST_DAILY_URL)
    Observable<WeatherDataDTO> getRxGeoWeatherDays(@Query("lat") double lat,
                                                 @Query("lon") double lon,
                                                 @Query(QUERY_LANGUAGE) String lang,
                                                 @Query(QUERY_DAY_COUNT) String count,
                                                 @Query(QUERY_TEMP_UNITS) String format,
                                                 @Query(QUERY_APIKEY) String key);
}
