package nl.han.oose.dea.spotitube.resources;

public class LoginResponse {
    private String token;
    private String name;

    public LoginResponse(String name, String token) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
