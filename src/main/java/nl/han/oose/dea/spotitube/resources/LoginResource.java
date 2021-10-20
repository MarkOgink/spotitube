package nl.han.oose.dea.spotitube.resources;

import nl.han.oose.dea.spotitube.services.ILoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    private ILoginService ILoginService;

    @Inject
    public void setILoginService(ILoginService iLoginService){
        this.ILoginService = iLoginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest user){
        return ILoginService.login(user);
    }
}