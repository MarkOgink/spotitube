package nl.han.oose.dea.spotitube.controller;

import nl.han.oose.dea.spotitube.controller.dto.Playlists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @GET
    public Response showPlaylists(@QueryParam("token") String token){
        System.out.println(token);
        Playlists playlists = new Playlists("1234-1234-1234");
        return Response.ok(playlists).build();
    }
}
