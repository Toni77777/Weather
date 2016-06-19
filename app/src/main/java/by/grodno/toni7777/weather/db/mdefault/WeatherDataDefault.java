package by.grodno.toni7777.weather.db.mdefault;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherDataDefault extends RealmObject {

    @PrimaryKey
    private String defaultKey;
    private String city;
    private RealmList<WeatherDayDefault> weatherDayDefault;

    public WeatherDataDefault() {
    }

    public WeatherDataDefault(String defaultKey, String city, RealmList<WeatherDayDefault> weatherDayDefault) {
        this.defaultKey = defaultKey;
        this.city = city;
        this.weatherDayDefault = weatherDayDefault;
    }

    public String getDefaultKey() {
        return defaultKey;
    }

    public void setDefaultKey(String defaultKey) {
        this.defaultKey = defaultKey;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RealmList<WeatherDayDefault> getWeatherDayDefault() {
        return weatherDayDefault;
    }

    public void setWeatherDayDefault(RealmList<WeatherDayDefault> weatherDayDefault) {
        this.weatherDayDefault = weatherDayDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataDefault that = (WeatherDataDefault) o;

        if (defaultKey != null ? !defaultKey.equals(that.defaultKey) : that.defaultKey != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return weatherDayDefault != null ? weatherDayDefault.equals(that.weatherDayDefault) : that.weatherDayDefault == null;

    }

    @Override
    public int hashCode() {
        int result = defaultKey != null ? defaultKey.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (weatherDayDefault != null ? weatherDayDefault.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDataDefault{" +
                "defaultKey='" + defaultKey + '\'' +
                ", city='" + city + '\'' +
                ", weatherDayDefault=" + weatherDayDefault +
                '}';
    }
}
