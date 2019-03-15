package nl.han.oose.dea.spotitube.controller;

import nl.han.oose.dea.spotitube.controller.dto.LoginRequest;
import nl.han.oose.dea.spotitube.controller.dto.LoginResponse;
import nl.han.oose.dea.spotitube.controller.dto.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest user){
        User test = new User();


        if(user.getUsername().equals(test.getUsername()) && user.getPassword().equals(test.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken("1234-1234-1234");
            loginResponse.setUser(user.getUsername());
            return Response.ok(loginResponse).build();
        }

        else return Response.status(403).build();
    }

}
