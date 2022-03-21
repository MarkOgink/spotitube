package nl.han.oose.dea.spotitube.resources;
import nl.han.oose.dea.spotitube.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackResource {
    private TrackService trackService;
    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksOutOfPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist){
        if(Token.authenticate(token)) return Response.ok().entity(trackService.getTracksOutOfPlaylist(forPlaylist)).build();
        else return Response.status(403).build();
    }
}
