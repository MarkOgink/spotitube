package nl.han.oose.dea.spotitube.tracks;

import nl.han.oose.dea.spotitube.util.Token;
import nl.han.oose.dea.spotitube.tracks.TrackResource;
import nl.han.oose.dea.spotitube.tracks.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TrackResourceTest {
    private TrackService mockedTrackService;
    private TrackResource sut;

    @BeforeEach
    void setUp() {
        this.sut = new TrackResource();
        this.mockedTrackService = Mockito.mock(TrackService.class);
        this.sut.setTrackService(mockedTrackService);
    }

    @Test
    void getTracksOutOfPlaylist() {
        int forPlaylist = 1;
        String token = Token.setToken("meron");
        sut.getTracksOutOfPlaylist(token, forPlaylist);
        Mockito.verify(mockedTrackService).getTracksOutOfPlaylist(forPlaylist);
    }

    @Test
    void getTracksOutOfPlaylistInvalidToken() {
        int forPlaylist = 1;
        var response = sut.getTracksOutOfPlaylist("", forPlaylist);
        assertEquals(403, response.getStatus());
    }
}
