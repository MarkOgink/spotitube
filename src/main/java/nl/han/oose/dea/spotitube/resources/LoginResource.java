package nl.han.oose.dea.spotitube.resources;

import nl.han.oose.dea.spotitube.domain.LoginRequest;
import nl.han.oose.dea.spotitube.domain.LoginResponse;
import nl.han.oose.dea.spotitube.domain.User;
import nl.han.oose.dea.spotitube.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    private LoginService loginService;
    @Inject
    public void setLoginService(LoginService loginService){
        this.loginService = loginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest user){
        User response = loginService.login(user);
        if(response != null) {
            LoginResponse loginResponse = new LoginResponse(Token.setToken(response.username), response.name);
            return Response.status(201).entity(loginResponse)
                    .build();
        }
        else return Response.status(401).build();
    }
}
