package nl.han.oose.dea.spotitube.resources;
import nl.han.oose.dea.spotitube.domain.PlaylistRequest;
import nl.han.oose.dea.spotitube.services.PlaylistService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private PlaylistService playlistService;
    @Inject
    public void setPlaylistService(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token){
        if(Token.authenticate(token)){
            return Response.ok().entity(playlistService.getPlaylists(Token.getUsername(token))).build();
        }
        else return Response.status(403).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistRequest playlist){
        if(Token.authenticate(token)) {
            return Response.status(201).entity(playlistService.addPlaylist(playlist.name)).build();
        }
        else return Response.status(403).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        if(Token.authenticate(token)) {
            return Response.ok().entity(playlistService.removePlaylist(id)).build();
        }
        else return Response.status(403).build();
    }

    @PUT
    @Path("/{id}")
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistRequest playlist) {
        if(Token.authenticate(token)){
            return Response.ok().entity(playlistService.editPlaylist(id, playlist.name)).build();
        }
        else return Response.status(403).build();
    }
}
