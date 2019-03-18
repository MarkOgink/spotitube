package nl.han.oose.dea.spotitube.controller;

import nl.han.oose.dea.spotitube.controller.dto.LoginResponse;
import nl.han.oose.dea.spotitube.controller.dto.Playlist;
import nl.han.oose.dea.spotitube.controller.dto.Playlists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    Playlists playlists;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token){
        if(token.equals("1234-1234-1234")){
            playlists = new Playlists();
            return Response.ok(playlists).build();
        }
        else return Response.status(403).build();
    }

    @GET
    @Path("/{forPlaylist}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("forPlaylist") int forPlaylist){
        playlists = new Playlists();
        for (int i = 0; i < playlists.getPlaylists().size(); i++) {
            if(playlists.getPlaylists().get(i).getId() == forPlaylist){
                System.out.println(playlists.getPlaylists().get(i).getTracks());
                return Response.ok(playlists.getPlaylists().get(i).getTracks()).build();
            }
        }
        return Response.status(403).build();
    }

//    @DELETE
//    public Response removePlaylist(@QueryParam("token") String token, @QueryParam("id") int id){
//        if(token.equals("1234-1234-1234")) {
//            playlists.removePlaylistFromPlaylists(playlists, id);
//            return Response.ok(playlists).build();
//        }
//        else return Response.status(403).build();
//    }


}
