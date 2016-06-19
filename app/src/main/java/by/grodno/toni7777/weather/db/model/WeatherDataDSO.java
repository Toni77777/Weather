package by.grodno.toni7777.weather.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherDataDSO extends RealmObject {

    @PrimaryKey
    private String city;
    private RealmList<WeatherDayDSO> weatherDayDSO;

    public WeatherDataDSO() {
    }

    public WeatherDataDSO(String city, RealmList<WeatherDayDSO> weatherDayDSO) {
        this.city = city;
        this.weatherDayDSO = weatherDayDSO;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RealmList<WeatherDayDSO> getWeatherDayDSO() {
        return weatherDayDSO;
    }

    public void setWeatherDayDSO(RealmList<WeatherDayDSO> weatherDayDSO) {
        this.weatherDayDSO = weatherDayDSO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataDSO that = (WeatherDataDSO) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return weatherDayDSO != null ? weatherDayDSO.equals(that.weatherDayDSO) : that.weatherDayDSO == null;

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (weatherDayDSO != null ? weatherDayDSO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDataDSO{" +
                "city='" + city + '\'' +
                ", weatherDayDSO=" + weatherDayDSO +
                '}';
    }
}
