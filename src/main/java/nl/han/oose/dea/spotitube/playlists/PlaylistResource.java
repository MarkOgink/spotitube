package nl.han.oose.dea.spotitube.playlists;
import nl.han.oose.dea.spotitube.playlists.PlaylistService;
import nl.han.oose.dea.spotitube.playlists.dto.PlaylistRequest;
import nl.han.oose.dea.spotitube.util.Token;
import nl.han.oose.dea.spotitube.tracks.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private PlaylistService playlistService;
    private TrackService trackService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService){
        this.playlistService = playlistService;
    }
    @Inject
    public void setTrackService(TrackService trackService) { this.trackService = trackService;}

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token){
        if(Token.authenticate(token)){
            return Response.ok().entity(trackService.addTracksToAllPlaylists(playlistService.getPlaylists(Token.getUsername(token)))).build();
        }
        else return Response.status(403).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistRequest playlist){
        if(Token.authenticate(token)) {
            return Response.status(201).entity(trackService.addTracksToAllPlaylists(playlistService.addPlaylist(playlist.name))).build();
        }
        else return Response.status(403).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        if(Token.authenticate(token)) {
            return Response.ok().entity(trackService.addTracksToAllPlaylists(playlistService.removePlaylist(id))).build();
        }
        else return Response.status(403).build();
    }

    @PUT
    @Path("/{id}")
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistRequest playlist) {
        if(Token.authenticate(token)){
            return Response.ok().entity(trackService.addTracksToAllPlaylists(playlistService.editPlaylist(id, playlist.name))).build();
        }
        else return Response.status(403).build();
    }
}
