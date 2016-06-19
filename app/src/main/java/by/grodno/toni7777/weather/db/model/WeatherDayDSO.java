package by.grodno.toni7777.weather.db.model;

import io.realm.RealmObject;

public class WeatherDayDSO extends RealmObject {

    private String cityName;
    private long time;
    private TemperatureDSO temperatureDSO;
    private WeatherDSO weatherDSO;
    private float pressure;
    private float humidity;
    private float speed;

    public WeatherDayDSO() {
    }

    public WeatherDayDSO(String cityName, long time, TemperatureDSO temperatureDSO, WeatherDSO weatherDSO, float pressure, float humidity, float speed) {
        this.cityName = cityName;
        this.time = time;
        this.temperatureDSO = temperatureDSO;
        this.weatherDSO = weatherDSO;
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

    public TemperatureDSO getTemperatureDSO() {
        return temperatureDSO;
    }

    public void setTemperatureDSO(TemperatureDSO temperatureDSO) {
        this.temperatureDSO = temperatureDSO;
    }

    public WeatherDSO getWeatherDSO() {
        return weatherDSO;
    }

    public void setWeatherDSO(WeatherDSO weatherDSO) {
        this.weatherDSO = weatherDSO;
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

        WeatherDayDSO that = (WeatherDayDSO) o;

        if (time != that.time) return false;
        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.speed, speed) != 0) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (temperatureDSO != null ? !temperatureDSO.equals(that.temperatureDSO) : that.temperatureDSO != null)
            return false;
        return weatherDSO != null ? weatherDSO.equals(that.weatherDSO) : that.weatherDSO == null;

    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (temperatureDSO != null ? temperatureDSO.hashCode() : 0);
        result = 31 * result + (weatherDSO != null ? weatherDSO.hashCode() : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDayDSO{" +
                "cityName='" + cityName + '\'' +
                ", time=" + time +
                ", temperatureDSO=" + temperatureDSO +
                ", weatherDSO=" + weatherDSO +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                '}';
    }
}
