package by.grodno.toni7777.weather.ui.model;

public class WeatherDayDVO {

    private String cityName;
    private long time;
    private TemperatureDVO temperature;
    private WeatherDVO weather;
    private float pressure;
    private float humidity;
    private float speed;


    public WeatherDayDVO() {
    }

    public WeatherDayDVO(String cityName, long time, TemperatureDVO temperature, WeatherDVO weather, float pressure, float humidity, float speed) {
        this.cityName = cityName;
        this.time = time;
        this.temperature = temperature;
        this.weather = weather;
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

    public TemperatureDVO getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureDVO temperature) {
        this.temperature = temperature;
    }

    public WeatherDVO getWeather() {
        return weather;
    }

    public void setWeather(WeatherDVO weather) {
        this.weather = weather;
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

        WeatherDayDVO that = (WeatherDayDVO) o;

        if (time != that.time) return false;
        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (Float.compare(that.humidity, humidity) != 0) return false;
        if (Float.compare(that.speed, speed) != 0) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (temperature != null ? !temperature.equals(that.temperature) : that.temperature != null)
            return false;
        return weather != null ? weather.equals(that.weather) : that.weather == null;

    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDayDVO{" +
                "cityName='" + cityName + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                ", weather=" + weather +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", speed=" + speed +
                '}';
    }
}
