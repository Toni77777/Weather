package by.grodno.toni7777.weather.db.mdefault;

import io.realm.RealmObject;

public class WeatherDayDefault extends RealmObject {

    private String cityName;
    private long time;
    private TemperatureDefault temperatureDefault;
    private WeatherDefault weatherDefault;
    private float pressure;
    private float humidity;
    private float speed;

    public WeatherDayDefault() {
    }

    public WeatherDayDefault(String cityName, long time, TemperatureDefault temperatureDefault, WeatherDefault weatherDefault, float pressure, float humidity, float speed) {
        this.cityName = cityName;
        this.time = time;
        this.temperatureDefault = temperatureDefault;
        this.weatherDefault = weatherDefault;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TemperatureDefault getTemperatureDefault() {
        return temperatureDefault;
    }

    public void setTemperatureDefault(TemperatureDefault temperatureDefault) {
        this.temperatureDefault = temperatureDefault;
    }

    public WeatherDefault getWeatherDefault() {
        return weatherDefault;
    }

    public void setWeatherDefault(WeatherDefault weatherDefault) {
        this.weatherDefault = weatherDefault;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDayDefault that = (WeatherDayDefault) o;

        if (time != that.time) return false;
        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.speed, speed) != 0) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (temperatureDefault != null ? !temperatureDefault.equals(that.temperatureDefault) : that.temperatureDefault != null)
            return false;
        return weatherDefault != null ? weatherDefault.equals(that.weatherDefault) : that.weatherDefault == null;

    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (temperatureDefault != null ? temperatureDefault.hashCode() : 0);
        result = 31 * result + (weatherDefault != null ? weatherDefault.hashCode() : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDayDefault{" +
                "cityName='" + cityName + '\'' +
                ", time=" + time +
                ", temperatureDefault=" + temperatureDefault +
                ", weatherDefault=" + weatherDefault +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                '}';
    }
}
