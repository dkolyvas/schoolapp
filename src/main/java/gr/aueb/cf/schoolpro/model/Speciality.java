package gr.aueb.cf.schoolpro.model;

public class Speciality {
    private int id;
    private String speciality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Speciality(int id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
