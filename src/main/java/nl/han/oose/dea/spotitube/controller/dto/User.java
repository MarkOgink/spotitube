package nl.han.oose.dea.spotitube.controller.dto;

public class User {
    public String username;
    public String password;

    public User(){
        this.username = "hallo";
        this.password = "test";
    }

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
