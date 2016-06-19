package by.grodno.toni7777.weather.db.model;

import io.realm.RealmObject;

public class TemperatureDSO extends RealmObject {

    private float day;
    private float night;

    public TemperatureDSO() {
    }

    public TemperatureDSO(float day, float night) {
        this.day = day;
        this.night = night;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureDSO that = (TemperatureDSO) o;

        if (Float.compare(that.day, day) != 0) return false;
        return Float.compare(that.night, night) == 0;

    }

    @Override
    public int hashCode() {
        int result = (day != +0.0f ? Float.floatToIntBits(day) : 0);
        result = 31 * result + (night != +0.0f ? Float.floatToIntBits(night) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TemperatureDSO{" +
                "day=" + day +
                ", night=" + night +
                '}';
    }
}
