package by.grodno.toni7777.weather.mvp;


import java.util.Locale;

import static by.grodno.toni7777.weather.util.Constants.*;

import by.grodno.toni7777.weather.network.NetworkService;
import by.grodno.toni7777.weather.network.model.WeatherDataDTO;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class WeatherPresenterImp implements WeatherPresenter {

    private WeatherView view;
    private NetworkService service;
    private Subscription subscription;

    public WeatherPresenterImp(WeatherView view, NetworkService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadRxData(String cityName) {
        view.showRxInProcess();
        Observable<WeatherDataDTO> weatherResponseObservable = (Observable<WeatherDataDTO>)
                service.getPreparedObservable(service.geWeatherService().getRxWeatherDays(cityName, Locale.getDefault().getLanguage(), COUNT_WEATHER_DAYS, TEMPERATURE_UNITS, APIKEY_VALUE), WeatherDataDTO.class, true, false);
        subscription = weatherResponseObservable.subscribe(new Observer<WeatherDataDTO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showRxError(e);
            }

            @Override
            public void onNext(WeatherDataDTO weatherData) {
                view.showRxData(weatherData);
            }

        });
    }

    @Override
    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadRxGeoData(double lat, double lon) {
        Observable<WeatherDataDTO> weatherResponseObservable = (Observable<WeatherDataDTO>)
                service.getPreparedObservable(service.geWeatherService().getRxGeoWeatherDays(lat, lon, Locale.getDefault().getLanguage(), COUNT_WEATHER_DAYS, TEMPERATURE_UNITS, APIKEY_VALUE), WeatherDataDTO.class, true, false);
        subscription = weatherResponseObservable.subscribe(new Observer<WeatherDataDTO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showRxError(e);
            }

            @Override
            public void onNext(WeatherDataDTO weatherData) {
                view.showRxGeoData(weatherData);
            }

        });
    }

}
