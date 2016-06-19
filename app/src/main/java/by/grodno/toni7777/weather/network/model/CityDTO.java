package by.grodno.toni7777.weather.network.model;

import com.google.gson.annotations.SerializedName;


public class CityDTO {

    private int id;
    private String name;
    private String country;
    private long population;
    @SerializedName("coord")
    private CoordinatesDTO сoordinates;

    public CityDTO() {
    }

    public CityDTO(int id, String name, String country, long population, CoordinatesDTO сoordinates) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.population = population;
        this.сoordinates = сoordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public CoordinatesDTO getCoord() {
        return сoordinates;
    }

    public void setCoord(CoordinatesDTO сoordinates) {
        this.сoordinates = сoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityDTO city = (CityDTO) o;

        if (id != city.id) return false;
        if (population != city.population) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (country != null ? !country.equals(city.country) : city.country != null) return false;
        return !(сoordinates != null ? !сoordinates.equals(city.сoordinates) : city.сoordinates != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (int) (population ^ (population >>> 32));
        result = 31 * result + (сoordinates != null ? сoordinates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", time='" + name + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", сoordinates=" + сoordinates +
                '}';
    }
}
