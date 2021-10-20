package nl.han.oose.dea.spotitube.domain;

public class User {
    public String token;
    public String name;

    public User(String name, String token) {
        this.token = token;
        this.name = name;
    }
}
