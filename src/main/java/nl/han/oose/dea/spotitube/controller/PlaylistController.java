package nl.han.oose.dea.spotitube.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @GET
    public Response showPlaylists(@QueryParam("token") String token){
        return null;
    }

}
