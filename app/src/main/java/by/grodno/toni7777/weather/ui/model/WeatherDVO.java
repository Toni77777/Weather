package by.grodno.toni7777.weather.ui.model;

public class WeatherDVO {

    private int id;
    private String main;
    private String description;

    public WeatherDVO() {
    }

    public WeatherDVO(int id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDVO that = (WeatherDVO) o;

        if (id != that.id) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherDVO{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
