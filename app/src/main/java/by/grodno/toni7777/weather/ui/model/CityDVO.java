package by.grodno.toni7777.weather.ui.model;

public class CityDVO {

    private String name;
    private String country;

    public CityDVO() {
    }

    public CityDVO(String name, String country) {
        this.name = name;
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityDVO cityDVO = (CityDVO) o;

        if (name != null ? !name.equals(cityDVO.name) : cityDVO.name != null) return false;
        return country != null ? country.equals(cityDVO.country) : cityDVO.country == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CityDVO{" +
                "time='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
