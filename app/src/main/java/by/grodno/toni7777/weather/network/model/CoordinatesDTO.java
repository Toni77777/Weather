package by.grodno.toni7777.weather.network.model;

import com.google.gson.annotations.SerializedName;



public class CoordinatesDTO {

    @SerializedName("lon")
    private float longitude;
    @SerializedName("lat")
    private float latitude;

    public CoordinatesDTO() {
    }

    public CoordinatesDTO(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinatesDTO that = (CoordinatesDTO) o;

        if (Float.compare(that.longitude, longitude) != 0) return false;
        return Float.compare(that.latitude, latitude) == 0;

    }

    @Override
    public int hashCode() {
        int result = (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoordinatesDTO{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
