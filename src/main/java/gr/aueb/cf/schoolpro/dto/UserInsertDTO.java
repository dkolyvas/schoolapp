package gr.aueb.cf.schoolpro.dto;

public class UserInsertDTO {

    private String username;
    private String password;
    public UserInsertDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
