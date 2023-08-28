package gr.aueb.cf.schoolpro.model;

public class City {
    private int id;
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
