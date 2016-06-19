package by.grodno.toni7777.weather.ui.model;

import java.util.List;

public class WeatherDataDVO {

    private CityDVO city;
    private String code;
    private String message;
    private List<WeatherDayDVO> weatherDays;

    public WeatherDataDVO() {
    }

    public WeatherDataDVO(CityDVO city, String code, String message, List<WeatherDayDVO> weatherDays) {
        this.city = city;
        this.code = code;
        this.message = message;
        this.weatherDays = weatherDays;
    }

    public CityDVO getCity() {
        return city;
    }

    public void setCity(CityDVO city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WeatherDayDVO> getWeatherDays() {
        return weatherDays;
    }

    public void setWeatherDays(List<WeatherDayDVO> weatherDays) {
        this.weatherDays = weatherDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataDVO that = (WeatherDataDVO) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return weatherDays != null ? weatherDays.equals(that.weatherDays) : that.weatherDays == null;

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (weatherDays != null ? weatherDays.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDataDVO{" +
                "city=" + city +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", weatherDays=" + weatherDays +
                '}';
    }
}
