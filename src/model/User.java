package model;

public class User {
    public long id;
    public String email;
    public String password;
    public String full_name;
    public String role_id;
    public User(){}

    public User(long id, String email, String password, String full_name, String role_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.role_id = role_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
