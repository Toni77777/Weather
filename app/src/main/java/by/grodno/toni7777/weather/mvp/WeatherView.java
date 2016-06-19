package by.grodno.toni7777.weather.mvp;

import by.grodno.toni7777.weather.network.model.WeatherDataDTO;

public interface WeatherView {

    void showRxData(WeatherDataDTO data);

    void showRxGeoData(WeatherDataDTO data);

    void showRxError(Throwable throwable);

    void showRxInProcess();

    void showCityDialog(String informationChange);
}
