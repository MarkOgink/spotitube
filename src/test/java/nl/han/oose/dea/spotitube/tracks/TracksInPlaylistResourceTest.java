package nl.han.oose.dea.spotitube.tracks;

import nl.han.oose.dea.spotitube.util.Token;
import nl.han.oose.dea.spotitube.tracks.playlist.TracksInPlaylistResource;
import nl.han.oose.dea.spotitube.tracks.dto.TrackRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TracksInPlaylistResourceTest {
    private TracksInPlaylistResource sut;
    private TrackService mockedTrackService;
    String token;
    int id;

    @BeforeEach
    void setUp() {
        this.sut = new TracksInPlaylistResource();
        this.mockedTrackService = Mockito.mock(TrackService.class);
        this.sut.setTrackService(mockedTrackService);
        token = Token.setToken("meron");
        id = 1;
    }

    @Test
    void getTracksInPlaylist() {
        sut.getTracksInPlaylist(token,id);
        Mockito.verify(mockedTrackService).getTracksInPlaylist(id);
    }

    @Test
    void getTracksInPlaylistInvalidToken() {
        var response = sut.getTracksInPlaylist("",id);
        assertEquals(403, response.getStatus());
    }

    @Test
    void addTrackToPlaylist() {
        TrackRequest trackRequest = new TrackRequest();
        sut.addTrackToPlaylist(token, id, trackRequest);
        Mockito.verify(mockedTrackService).addTrackToPlaylist(id, trackRequest);
    }

    @Test
    void addTrackToPlaylistInvalidToken() {
        TrackRequest trackRequest = new TrackRequest();
        var response = sut.addTrackToPlaylist("",id, trackRequest);
        assertEquals(403, response.getStatus());
    }

    @Test
    void deleteTrackFromPlaylist() {
        sut.deleteTrackFromPlaylist(token,id,1);
        Mockito.verify(mockedTrackService).deleteTrack(id,1);
    }

    @Test
    void deleteTrackFromPlaylistInavlidToken() {
        var response = sut.deleteTrackFromPlaylist("", id, 1);
        assertEquals(403, response.getStatus());
    }
}
