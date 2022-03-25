package nl.han.oose.dea.spotitube.login;

import nl.han.oose.dea.spotitube.login.dto.User;
import nl.han.oose.dea.spotitube.login.dto.LoginRequest;
import nl.han.oose.dea.spotitube.login.dto.LoginResponse;
import nl.han.oose.dea.spotitube.util.Token;

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
        if(user.user!=null&&user.password!=null){
            User response = loginService.login(user);
            if(response != null) {
                LoginResponse loginResponse = new LoginResponse(Token.setToken(response.username), response.name);
                return Response.status(201).entity(loginResponse)
                        .build();
            }
            else return Response.status(401).build();
        }
        else return Response.status(400).build();
    }
}
