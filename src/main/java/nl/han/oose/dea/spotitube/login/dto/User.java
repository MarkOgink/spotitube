package nl.han.oose.dea.spotitube.login.dto;

public class User {
    public String username;
    public String password;
    public String name;

    public User(String username, String name, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
