package nl.han.oose.dea.spotitube.domain;

public class User {
    public String username;
    public String password;
    public String token;
    public String name;

    public User(String username, String password, String name, String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.token = token;
    }
}
