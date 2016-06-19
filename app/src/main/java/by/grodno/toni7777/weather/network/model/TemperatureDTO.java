package by.grodno.toni7777.weather.network.model;

import com.google.gson.annotations.SerializedName;


public class TemperatureDTO {

    private float day;
    private float min;
    private float max;
    private float night;
    @SerializedName("eve")
    private float evening;
    @SerializedName("morn")
    private float morning;

    public TemperatureDTO() {
    }

    public TemperatureDTO(float day, float min, float max, float night, float eve, float morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.evening = eve;
        this.morning = morn;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEvening() {
        return evening;
    }

    public void setEvening(float evening) {
        this.evening = evening;
    }

    public float getMorning() {
        return morning;
    }

    public void setMorning(float morning) {
        this.morning = morning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureDTO temp = (TemperatureDTO) o;

        if (Float.compare(temp.day, day) != 0) return false;
        if (Float.compare(temp.min, min) != 0) return false;
        if (Float.compare(temp.max, max) != 0) return false;
        if (Float.compare(temp.night, night) != 0) return false;
        if (Float.compare(temp.evening, evening) != 0) return false;
        return Float.compare(temp.morning, morning) == 0;

    }

    @Override
    public int hashCode() {
        int result = (day != +0.0f ? Float.floatToIntBits(day) : 0);
        result = 31 * result + (min != +0.0f ? Float.floatToIntBits(min) : 0);
        result = 31 * result + (max != +0.0f ? Float.floatToIntBits(max) : 0);
        result = 31 * result + (night != +0.0f ? Float.floatToIntBits(night) : 0);
        result = 31 * result + (evening != +0.0f ? Float.floatToIntBits(evening) : 0);
        result = 31 * result + (morning != +0.0f ? Float.floatToIntBits(morning) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TemperatureDTO{" +
                "day=" + day +
                ", min=" + min +
                ", max=" + max +
                ", night=" + night +
                ", evening=" + evening +
                ", morning=" + morning +
                '}';
    }
}
