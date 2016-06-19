package by.grodno.toni7777.weather.network.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherDayDTO {

    @SerializedName("dt")
    private long time;
    @SerializedName("temp")
    private TemperatureDTO temperature;
    private float pressure;
    private float humidity;
    private List<WeatherDTO> weather;
    private float speed;
    @SerializedName("deg")
    private float windDirection;
    @SerializedName("cloudiness")
    private float cloudiness;
    private float rain;

    public WeatherDayDTO() {
    }

    public WeatherDayDTO(long dt, TemperatureDTO temp, float pressure, float humidity, List<WeatherDTO> weather, float speed, float deg, float clouds, float rain) {
        this.time = dt;
        this.temperature = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.windDirection = deg;
        this.cloudiness = clouds;
        this.rain = rain;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TemperatureDTO getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureDTO temperature) {
        this.temperature = temperature;
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

    public List<WeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDTO> weather) {
        this.weather = weather;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(float windDirection) {
        this.windDirection = windDirection;
    }

    public float getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(float cloudiness) {
        this.cloudiness = cloudiness;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDayDTO that = (WeatherDayDTO) o;

        if (time != that.time) return false;
        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.speed, speed) != 0) return false;
        if (Float.compare(that.windDirection, windDirection) != 0) return false;
        if (Float.compare(that.cloudiness, cloudiness) != 0) return false;
        if (Float.compare(that.rain, rain) != 0) return false;
        if (temperature != null ? !temperature.equals(that.temperature) : that.temperature != null) return false;
        return !(weather != null ? !weather.equals(that.weather) : that.weather != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (time ^ (time >>> 32));
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        result = 31 * result + (windDirection != +0.0f ? Float.floatToIntBits(windDirection) : 0);
        result = 31 * result + (cloudiness != +0.0f ? Float.floatToIntBits(cloudiness) : 0);
        result = 31 * result + (rain != +0.0f ? Float.floatToIntBits(rain) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDayDTO{" +
                "time=" + time +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", weather=" + weather +
                ", speed=" + speed +
                ", windDirection=" + windDirection +
                ", cloudiness=" + cloudiness +
                ", rain=" + rain +
                '}';
    }
}
