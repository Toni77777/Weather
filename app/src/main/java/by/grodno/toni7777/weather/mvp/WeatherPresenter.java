package by.grodno.toni7777.weather.mvp;

public interface WeatherPresenter {

    void loadRxData(String cityName);

    void rxUnSubscribe();

    void loadRxGeoData(double lat, double lon);
}
