package nl.han.oose.dea.spotitube.controller.dto;

public class LoginRequest {
    public String user;
    public String password;


    public String getUsername() {
        return user;
    }

    public void setUsername(String username) {
        this.user = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
