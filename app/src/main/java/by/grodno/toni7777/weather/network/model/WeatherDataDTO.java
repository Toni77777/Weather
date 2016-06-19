package by.grodno.toni7777.weather.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherDataDTO {

    private CityDTO city;
    @SerializedName("cod")
    private String code;
    private String message;
    @SerializedName("cnt")
    private int dayCount;
    @SerializedName("list")
    private List<WeatherDayDTO> weatherDays;

    public WeatherDataDTO() {
    }

    public WeatherDataDTO(CityDTO city, String code, String message, int dayCount, List<WeatherDayDTO> weatherDays) {
        this.city = city;
        this.code = code;
        this.message = message;
        this.dayCount = dayCount;
        this.weatherDays = weatherDays;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
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

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public List<WeatherDayDTO> getWeatherDays() {
        return weatherDays;
    }

    public void setWeatherDays(List<WeatherDayDTO> weatherDays) {
        this.weatherDays = weatherDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataDTO that = (WeatherDataDTO) o;

        if (dayCount != that.dayCount) return false;
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
        result = 31 * result + dayCount;
        result = 31 * result + (weatherDays != null ? weatherDays.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDataDTO{" +
                "city=" + city +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", dayCount=" + dayCount +
                ", weatherDays=" + weatherDays +
                '}';
    }
}
