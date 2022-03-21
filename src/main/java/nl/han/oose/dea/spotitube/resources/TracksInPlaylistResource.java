package nl.han.oose.dea.spotitube.resources;

import nl.han.oose.dea.spotitube.domain.TrackRequest;
import nl.han.oose.dea.spotitube.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists/{id}/tracks")
public class TracksInPlaylistResource {
    private TrackService trackService;
    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        if(Token.authenticate(token)) return Response.ok().entity(trackService.getTracksInPlaylist(id)).build();
        else return Response.status(403).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int id, TrackRequest track){
        if(Token.authenticate(token)) {
            return Response.ok().entity(trackService.addTrackToPlaylist(id, track)).build();
        }
        else return Response.status(403).build();
    }

    @Path("/{track}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlist, @PathParam("track") int track){
        if(Token.authenticate(token)){
            return Response.ok().entity(trackService.deleteTrack(playlist, track)).build();
        }
        return Response.status(403).build();
    }
}
