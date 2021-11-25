package Dto;

public class LoginDTO {

       private String id;
       private String password;
       private String role;

    public LoginDTO(String id, String password) {
        this.setId(id);
        this.setPassword(password);
    }

    public LoginDTO() {
    }

    public LoginDTO(String id, String password, String role) {
        this.setId(id);
        this.setPassword(password);
        this.setRole(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
