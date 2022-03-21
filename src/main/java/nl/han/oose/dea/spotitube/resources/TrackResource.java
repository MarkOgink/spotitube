package nl.han.oose.dea.spotitube.resources;

import nl.han.oose.dea.spotitube.domain.TrackRequest;
import nl.han.oose.dea.spotitube.services.TrackService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class TrackResource {
    TrackService trackService = new TrackService();

    @GET
    @Path("/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksOutOfPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist){
        if(Token.authenticate(token)) return Response.ok().entity(trackService.getTracksOutOfPlaylist(forPlaylist)).build();
        else return Response.status(403).build();
    }

    @Path("/playlists/{id}/tracks")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        if(Token.authenticate(token)) return Response.ok().entity(trackService.getTracksInPlaylist(id)).build();
        else return Response.status(403).build();
    }

    @Path("/playlists/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int id, TrackRequest track){
        if(Token.authenticate(token)) {
            return Response.ok().entity(trackService.addTrackToPlaylist(id, track)).build();
        }
        else return Response.status(403).build();
    }

    @Path("/playlists/{playlist}/tracks/{track}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlist") int playlist, @PathParam("track") int track){
        if(Token.authenticate(token)){
            return Response.ok().entity(trackService.deleteTrack(playlist, track)).build();
        }
        return Response.status(403).build();
    }
}
